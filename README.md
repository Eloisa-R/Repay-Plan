# Repayment Plan
This tool allows to calculate a repayment plan for a loan. Using a form, the user can input the total amount of the loan,
the nominal interest rate, the duration (in months) of the repayment and the start date of the repayment
(which has to be a date in the future).

Caveats:
- When the principal calculation (annuity - interest) exceeded the outstanding principal amount, I took the outstanding
principal amount as instructed.
- However when sometimes (as a consequence, possibly, of rounding numbers) the remaining outstanding princial of the last
installment wasn't 0 but 0.02, I left it like that since this case wasn't handled in the instructions and I didn't want
to alter the calculations

# To use this tool

Download or clone the repository to your local machine. Then go to the root directory of this repo and run:

mvn install clean

After the build succeeds, run:

java -jar backend/target/backend-0.0.1-SNAPSHOT.jar

This should start the application on your localhost.
