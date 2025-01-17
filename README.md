# Deploy

## Python ke EXE

### Requirements
- Python 3.x : https://www.python.org/ftp/python/3.13.1/python-3.13.1-amd64.exe
- Visual Studio Code : https://code.visualstudio.com/sha/download?build=stable&os=win32-arm64-user 

- pip
Jika Python meminta untuk upgdare pip, jalankan ini:
``` bash
python.exe -m pip install --upgrade pip
```

- pyinstaller : 

Setelah mengunduh dan melakukan instalasi Python, buka Command Prompt (Run as Administrator) jalankan perintah: 
``` bash
pip install pyinstaller
```

Tutup Command Promot.

### Program

Buka kembali Command Prompt, tentukan lokasi proyek lalu jalankan Visual Studio Code:
``` bash
code Alarm.py
```

Tuliskan Kode berikut:
``` python
import tkinter as tk
from tkinter import messagebox
import datetime
import threading
import time

# Fungsi untuk memperbarui jam
def update_time():
    while True:
        current_time = datetime.datetime.now().strftime("%H:%M:%S")
        clock_label.config(text=current_time)
        time.sleep(1)

# Fungsi untuk memeriksa alarm
def check_alarm():
    while True:
        now = datetime.datetime.now().strftime("%H:%M:%S")
        if now == alarm_time.get():
            messagebox.showinfo("Alarm", "Alarm Berbunyi!")
            alarm_time.set("")  # Reset alarm setelah berbunyi
        time.sleep(1)

# Fungsi untuk memulai alarm
def start_alarm():
    if alarm_time.get():
        try:
            time.strptime(alarm_time.get(), "%H:%M:%S")  # Validasi format waktu
            threading.Thread(target=check_alarm, daemon=True).start()
            messagebox.showinfo("Info", "Alarm disetel untuk pukul " + alarm_time.get())
        except ValueError:
            messagebox.showerror("Error", "Format waktu tidak valid! Gunakan HH:MM:SS")
    else:
        messagebox.showerror("Error", "Silakan masukkan waktu alarm")

# Inisialisasi antarmuka pengguna
root = tk.Tk()
root.title("Jam dengan Alarm")
root.geometry("300x200")
root.resizable(False, False)

# Tambahkan ikon aplikasi (gunakan file .ico jika tersedia)
try:
    root.iconbitmap("icon.ico")
except:
    pass

# Label untuk jam
clock_label = tk.Label(root, text="--:--:--", font=("Helvetica", 48))
clock_label.pack(pady=20)

# Entry untuk waktu alarm
alarm_time = tk.StringVar()
alarm_entry = tk.Entry(root, textvariable=alarm_time, font=("Helvetica", 14), justify="center")
alarm_entry.pack(pady=10)
alarm_entry.insert(0, "HH:MM:SS")

# Tombol untuk menyetel alarm
set_alarm_button = tk.Button(root, text="Set Alarm", font=("Helvetica", 14), command=start_alarm)
set_alarm_button.pack(pady=10)

# Jalankan thread untuk memperbarui waktu
threading.Thread(target=update_time, daemon=True).start()

# Jalankan aplikasi
root.mainloop()
```

Unduh icon dari sini : https://icon-icons.com/downloadimage.php?id=108740&root=1617/ICO/512/&file=3700456-alarm-clock-date-time-timer-tools_108740.ico dan jangan lupa rename menjadi icon.ico.

Tempatkan di folder yang sama dengan file Alarm.py

### Build

Jalankan perintah berikut:
``` bash
pyinstaller --onefile --windowed --icon=icon.ico Alarm.py
```

Jika tidak berhasil karena pyinstaller tidak terdaftar di path, coba ini:
``` bash
python -m PyInstaller --onefile --windowed --icon=icon.ico Alarm.py
```

Hasil build dapat dilihat di dalam folder dist.