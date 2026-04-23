
Vehicle_cost = {"Economy": 10, "Premium": 18, "SUV": 25}
def calculate_fare(km, type, hour):
    try:
        total = km * Vehicle_cost[type]
    except KeyError:
        return None

    if 17 <= hour <= 20:
        total *= 1.5

    return total

km = float(input("Enter distance (in km): "))
type = input("Enter vehicle type (Economy / Premium / SUV): ").capitalize()
hour = int(input("Enter hour of day (0-23): "))

fare = calculate_fare(km, type, hour)

if fare is None:
    print("\nService Not Available")
else:
    surge = "Yes" if 17 <= hour <= 20 else "No"
    print("Ride Receipt")
    print(f"Distance      : {km} km")
    print(f"Vehicle Type  : {type}")
    print(f"Time (Hour)   : {hour}")
    print(f"Rate per km   : ₹{Vehicle_cost[type]}")
    print(f"Surge Applied : {surge}")
    print(f"Total Fare    : ₹{fare:.2f}")
