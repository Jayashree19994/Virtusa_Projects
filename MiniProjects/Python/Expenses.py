import csv
from datetime import datetime
import matplotlib.pyplot as plt

FILE = "expenses.csv"

def add():
    date = input("Date (YYYY-MM-DD or Enter): ")
    if not date:
        date = datetime.today().strftime('%Y-%m-%d')

    # CATEGORY validation (only letters allowed)
    cat = input("Category: ")
    if not cat.isalpha():
        print("Category must contain only letters!")
        return

    # AMOUNT validation (must be number)
    try:
        amt = float(input("Amount: "))
    except ValueError:
        print("Amount must be a number!")
        return

    with open(FILE, "a", newline="") as f:
        csv.writer(f).writerow([date, cat, amt])

    print("Added successfully!")

def summary():
    month = input("Month (YYYY-MM): ")
    data = {}

    try:
        with open(FILE) as f:
            for row in csv.reader(f):
                if row[0].startswith(month):
                    data[row[1]] = data.get(row[1], 0) + float(row[2])
    except:
        print("No data")
        return

    print("\nSummary:")
    for k, v in data.items():
        print(k, ":", v)

    if data:
        plt.pie(data.values(), labels=data.keys(), autopct='%1.1f%%')
        plt.show(block=False)
        plt.pause(10)
        plt.close()

while True:
    print("\n1.Add  2.Summary  3.Exit")
    ch = input("Choice: ")

    if ch == "1":
        add()
    elif ch == "2":
        summary()
    elif ch == "3":
        break
