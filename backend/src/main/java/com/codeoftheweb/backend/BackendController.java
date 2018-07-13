package com.codeoftheweb.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "http://localhost:8081", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
@RestController
public class BackendController {


    @Autowired
    private AnnuityRepository annuityRepository;
    @Autowired
    private InstallmentRepository installmentRepository;

    public BackendController() {
    }


    public void createInstallments(Annuity newAnnuity) {
        double inputPrinc = newAnnuity.getLoanAmount();
        for (int i=0; i < newAnnuity.getDuration(); i++) {
            newAnnuity.addInstallment(new Installment(i,newAnnuity.getAnnuity(),newAnnuity.getStartDate(), inputPrinc,newAnnuity.getNominalIR()));
            List<Installment> allInst = newAnnuity.getRepaymentplan();
            Installment lastInstallment = allInst.get(allInst.size()-1);
            installmentRepository.save(lastInstallment);
            inputPrinc = lastInstallment.getRemainPrinc();
        }

    }

    public List<Map<String,Object>> createInstList(Annuity newAnnuity){
        List<Map<String,Object>> InstList = new ArrayList<>();
        for (Installment installment: newAnnuity.getRepaymentplan()) {
         LinkedHashMap<String,Object> installmentMap = new LinkedHashMap<>();
        installmentMap.put("borrowerPaymentAmount", installment.getAnnuity());
        installmentMap.put("date", installment.getStartDate());
        installmentMap.put("initialOutstandingPrincipal", installment.getLoanAmount());
        installmentMap.put("interest", installment.getInterest());
        installmentMap.put("principal", installment.getPrincipal());
        installmentMap.put("remainingOutstandingPrincipal", installment.getRemainPrinc());
        InstList.add(installmentMap);
        }
        return InstList;
    }

    @RequestMapping(
            value = "/generate-plan",
            method = RequestMethod.POST)
    public ResponseEntity<List<Map<String,Object>>> getRepayPlan (@RequestBody Map<String,String> inputData) throws ParseException {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);

        Annuity newAnnuity = new Annuity(Double.parseDouble(inputData.get("loanAmount")),
                Double.parseDouble(inputData.get("nominalRate")),Integer.parseInt(inputData.get("duration")),
                format.parse(inputData.get("startDate")));
        annuityRepository.save(newAnnuity);
        createInstallments(newAnnuity);
        return new ResponseEntity<>(createInstList(newAnnuity),HttpStatus.CREATED);
    }

}
