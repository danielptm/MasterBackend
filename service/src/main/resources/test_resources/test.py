import data

def end_of_night_till(thousands, fiveHundreds, hundreds, fifties, twenties, tens, fives, ones):
    #Returns the amount of money in the till at the end of the night.
    return (thousands * 1000) + (fiveHundreds * 500) + (hundreds * 100) + (fifties * 50) (twenties * 20) + (tens * 10) + (fives * 5) + (ones * 1)

def next_till(thousands, fiveHundreds, hundreds, fifties, twenties, tens, fives, ones):
    return end_of_night_till(thousands, fiveHundreds, hundreds, fifties, twenties, tens, fives, ones) - \
    toBank(till, bank, thousands, fiveHundreds, hundreds)
    

def toBank(till, bank, thousands, fiveHundreds, hundreds):
    while till> 1800 and thousands > 0:
        thousands -= 1
        till -= 1000
        bank += 1000
    
    while till > 1800 and fiveHundreds > 0:
        fiveHundreds -= 1
        till -= 500
        bank += 500

    while till > 1800 and hundreds > 0:
        hundreds -= 1
        till -= 100
        bank += 100

    return till, bank, thousands, fiveHundreds, hundreds
    
def next_till():
    #This should be the amount of money in the till at the end of the night minus the amount that is going to the bank
    pass
