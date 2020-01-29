# TL19-template

Template repository, used for NTUA/ECE Software Engineering, 2019-2020

Το αρχείο αυτό περιέχει οδηγίες για το στήσιμο του git repository που θα
χρησιμοποιήσετε.  Στο τέλος, θα το αντικαταστήσετε με το `README.md` που
θα περιγράφει το δικό σας project.


## Στήσιμο του repository

Αν σας αρκεί να ξεκινήσετε με αυτό το (κενό) template repository και να
προσθέσετε εκεί ό,τι γράψετε, τότε είστε ευτυχισμένοι και έτοιμοι:

```
git clone git@github.com:ntua/TL19-nn
```

όπου φυσικά θα αντικαταστήσετε το `nn` με τον αριθμό της ομάδας σας.

Αν είχατε ήδη αρχίσει να δουλεύετε σε κάποιο δικό σας git repository,
τότε έχετε τις εξής **εναλλακτικές** επιλογές.

> Οι δύο τελευταίες είναι κάπως επικίνδυνες --- διαβάστε πρώτα πώς δουλεύει
> το `git` πριν τις εφαρμόσετε και βεβαιωθείτε ότι καταλαβαίνετε ακριβώς
> τι κάνουν οι εντολές που εκτελείτε.
> Θεωρήστε ότι σας προειδοποιήσαμε και, disclaimer, δεν κάνουμε git support!
> Αν χαλάσετε το repository σας, λυπούμαστε πολύ αλλά είναι δικό σας πρόβλημα...

1.  Να αντιγράψετε τα αρχεία από το δικό σας repository στο παρόν,
    να τα κάνετε `git add`, `git commit` και `git push`.  Αυτή είναι
    η απλούστερη λύση, αλλά έχει το μειονέκτημα ότι **θα χάσετε το
    commit history** από το δικό σας repository.

2.  Να μεταφέρετε το δικό σας repository **σβήνοντας** τα περιεχόμενα
    του παρόντος.  Ξεκινώντας από ένα **clean** working directory του
    δικού σας υπάρχοντος repository:

    ```
    git remote add official git@github.com:ntua/TL19-nn.git
    git push -f official master
    ```

    Αν έχετε και άλλα branches, π.χ. κάποιο που λέγεται `other-branch`,
    μπορείτε να τα κάνετε `git push` και εκείνα.

    ```
    git push official other-branch
    ```

    Στη συνέχεια, μπορείτε να κάνετε ένα φρέσκο `git clone` και να δουλεύετε
    στο παρόν repos.

3.  Να μεταφέρετε το history από το δικό σας repository **προσθέτοντας**
    στο παρόν.  Ξεκινώντας από ένα **clean** working directory του δικού
    σας υπάρχοντος repository:

    ```
    git remote add official git@github.com:ntua/TL19-nn.git
    git push official master:our-master
    ```

    Αν έχετε και άλλα branches, π.χ. κάποιο που λέγεται `other-branch`,
    μπορείτε να τα κάνετε `git push` και εκείνα.

    ```
    git push official other-branch
    ```

    Στη συνέχεια, μπορείτε να κάνετε ένα φρέσκο `git clone` και να δουλεύετε
    στο παρόν repos.  Το δικό σας `master` branch θα λέγεται `our-master`.
    Μπορείτε να το κάνετε rebase πάνω στο `master` του παρόντος repository,
    με την παραπάνω διαδικασία:

    ```
    git checkout our-master
    git checkout -b rebased-master
    git rebase origin/master
    ```

    Αναλόγως αν έχετε ακολουθήσει το directory structure και πόσο τυχεροί
    είστε, είναι πιθανό να χρειαστεί να επιλύσετε κάποια conflicts.

    Στη συνέχεια, αν το παραπάνω rebase τελειώσει επιτυχώς, μπορείτε να
    μεταφέρετε το αποτέλεσμα στο master branch και να ξεφορτωθείτε τα
    πλέον άχρηστα branches

    ```
    git checkout master
    git merge rebased-master
    git branch -d rebased-master
    git branch -D our-master
    ```


## Directory structure

Δομήστε τα παραδοτέα σας ως εξής.  Με αστεράκι (\*) είναι σημειωμένα όσα
παραδοτέα είναι υποχρεωτικά μόνο για πολυπληθείς ομάδες (βλ. οδηγίες στο
  moodle και στο [αρχείο παραδοτέων](deliverables.docx)).
Η ίδια δομή υπάρχει στο [template repository](https://github.com/saikos/softeng19b)
που παρουσίασε στο μάθημα ο κ. Σαΐδης.

**/documentation**
- Documentation: Διαγράμματα UML Activity (in ONE vpp file).
- Documentation: Διαγράμματα UML Class (in ONE vpp file).
- Documentation: Διαγράμματα UML Component (in ONE vpp file).
- Documentation: Διαγράμματα UML Deployment (in ONE vpp file).
- Documentation: Διαγράμματα UML Sequence (in ONE vpp file).
- Documentation: Εγγραφο SRS - Software Requirements Specification.
- Documentation: Εγγραφο StRS - Stakeholders Requirements Specification.

**/back-end**
- Code-testing: Back-end functional tests.
- Code-testing: Back-end unit tests.
- Code-testing: RESTful API.
- Code-testing: Ανάλυση επιδόσεων του REST API ("benchmarking") (\*).
- Code-testing: Λειτουργίες χρέωσης του REST API (\*).
- Code-testing: Πηγαίος κώδικας εφαρμογής για εισαγωγή, διαχείριση και πρόσβαση σε πρωτογενή δεδομένα (backend).

**/cli-client**
- Code-testing: CLI functional tests.
- Code-testing: CLI unit tests.
- Code-testing: Command line interface (CLI).
- Code-testing: Ανάλυση επιδόσεων του REST API ("benchmarking") (\*).
- Code-testing: Λειτουργίες χρέωσης του REST API (\*).

**/front-end**
- Code-testing: Front-end tests.
- Code-testing: Front-end παρουσίασης δεδομένων σε περιβάλλον web.
- Code-testing: Ανάλυση επιδόσεων του REST API ("benchmarking") (\*).
- Code-testing: Λειτουργίες χρέωσης του REST API (\*).

**/mobile-app**
- Code-testing: Mobile app tests (\*).
- Code-testing: Mobile εφαρμογή παρουσίασης δεδομένων σε smartphone (\*).
