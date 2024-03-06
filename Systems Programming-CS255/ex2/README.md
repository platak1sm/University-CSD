Emmanouil Platakis, 4730

a) οι συναρτήσεις ms_copy, ms_ncopy, ms_concat, ms_nconcat δεν είναι δυνατόν να καλέσουν την assert και να ελέγξουν αν η μνήμη προορισμού όπου γράφεται το αποτέλεσμα είναι αρκετά μεγάλη, η assert ελέγχει απλά αν ενα string είναι NULL 


b) όχι και δε χρειάζεται διότι είναι type size_t  το οποίο είναι όμως unsigned