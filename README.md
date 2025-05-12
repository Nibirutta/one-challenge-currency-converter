# 💱 Real-Time Currency Converter (Java)

[![Java](https://img.shields.io/badge/Java-17+-ED8B00?logo=java&logoColor=white)](https://www.oracle.com/java/)

A command-line currency converter that fetches real-time exchange rates from external APIs and provides seamless conversions between global currencies.

## ✨ Features

- **Real-time exchange rates** using [ExchangeRate-API](https://www.exchangerate-api.com/).
- **Interactive terminal interface** with currency code validation.
- **Multi-conversion mode** (repeat conversions without restarting).
- **Error handling** for invalid inputs/API failures.
- **Clean architecture** separating API logic from UI.

## 🚀 How It Works

1. The program displays available currency codes (e.g., `USD`, `EUR`, `BRL`).
2. User selects:
    - Base currency (what you're converting *from*)
    - Target currency (what you're converting *to*)
    - Amount to convert
3. Program fetches current rate from API and calculates conversion.
4. Results displayed in realtime.
5. Option to repeat or exit.