import tkinter as tk
from tkinter import *
import tkinter.scrolledtext as scrolledtext
from tkinter import messagebox, filedialog
from pyzbar.pyzbar import decode, ZBarSymbol
import cv2
import pyautogui
import numpy as np
import threading
from PIL import Image, ImageTk, ImageDraw
import os
import datetime

class App:
    
    def __init__(self, font_video=0):

        global config_cred

        config_cred = True


        self.active_camera = False
        self.info = []
        self.codes_seen = set()  # Aquí almacenamos los códigos ya escaneados
        self.appName = 'Lector QR Empleados'
        self.ventana = Tk()
        self.ventana.title(self.appName)
        self.ventana['bg'] = 'black'
        self.font_video = font_video
        Label(self.ventana, text=self.appName, font=15, bg='blue',
              fg='white').pack(side=TOP, fill=BOTH)
        Button(self.ventana, text="Guardar información", bg='light blue', command=self.guardar).pack(side=BOTTOM)

        self.display = scrolledtext.ScrolledText(self.ventana, width=86, background='snow3'
                                                 , height=4, padx=10, pady=10, font=('Arial', 10))
        self.display.pack(side=BOTTOM)

        self.canvas = Canvas(self.ventana, bg='black', width=640, height=0)
        self.canvas.pack()
        Button(self.ventana, text="Cargar archivo", width=29, bg='goldenrod2', activebackground='red', command=self.abrir).pack(side=LEFT)
        self.btnCamera = Button(self.ventana, text="Iniciar lectura por cámara", width=30, bg='goldenrod2',
                                activebackground='red', command=self.active_cam)
        self.btnCamera.pack(side=LEFT)
        Button(self.ventana, text="Detectar en pantalla", width=29, bg='goldenrod2', activebackground='red', command=self.screen_shot).pack(side=RIGHT)

        self.ventana.mainloop()

    def guardar(self):
        if len(self.display.get('1.0', END)) > 1:
            documento = filedialog.asksaveasfilename(initialdir="/",
                                                     title="Guardar en", defaultextension='.txt')
            if documento != "":
                archivo_guardar = open(documento, "w", encoding="utf-8")
                linea = ""
                for c in str(self.display.get('1.0', END)):
                    linea = linea + c
                archivo_guardar.write(linea)
                archivo_guardar.close()
                messagebox.showinfo("Guardado", "Información guardada en \'{}\'".format(documento))

    def abrir(self):
        ruta = filedialog.askopenfilename(initialdir="/", title="Seleccionar archivo",
                                          filetypes=(("png files", "*.png"), ("jpg files", "*.jpg")))
        if ruta != "":
            archivo = cv2.imread(ruta)
            self.info = decode(archivo)
            if self.info != []:
                for i in self.info:
                    texto = i[0].decode('utf-8')
                    if texto not in self.codes_seen:  # Verifica si el código ya ha sido procesado
                        tiempo = datetime.datetime.now()
                        self.codes_seen.add(texto)
                        self.display.insert(END, texto + " -- "+ str(tiempo) + '\n')
            else:
                messagebox.showwarning("Archivo no válido", "No se detectó el código QR.")

    def screen_shot(self):
        pyautogui.screenshot("QRsearch_screenshoot.jpg")
        archivo = cv2.imread("QRsearch_screenshoot.jpg")
        self.info = decode(archivo)
        if self.info != []:
            for i in self.info:
                texto = i[0].decode('utf-8')
                if texto not in self.codes_seen:  # Verifica si el código ya ha sido procesado
                    tiempo = datetime.datetime.now()
                    self.codes_seen.add(texto)
                    self.display.insert(END, texto + " -- "+ str(tiempo) + '\n')
                    
        else:
            messagebox.showwarning("Código QR no encontrado", "No se detectó el código QR")
        os.remove("QRsearch_screenshoot.jpg")

    def visor(self):
        ret, frame = self.get_frame()
        if ret:
            self.photo = ImageTk.PhotoImage(image=Image.fromarray(frame))
            self.canvas.create_image(0, 0, image=self.photo, anchor=NW)
            self.ventana.after(15, self.visor)

    def active_cam(self):
        if self.active_camera == False:
            self.active_camera = True
            self.VideoCaptura()
            self.visor()
        else:
            self.active_camera = False
            # self.codelist = []
            self.btnCamera.configure(text="Iniciar lectura por cámara")
            self.vid.release()
            self.canvas.delete('all')
            self.canvas.configure(height=0)

    def capta(self, frm):
        self.info = decode(frm)
        cv2.putText(frm, "Muestre el codigo QR delante de la camara para su lectura", (84, 37), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 255, 0), 2)
        if self.info != []:
            for code in self.info:
                texto = code[0].decode('utf-8')
                if texto not in self.codes_seen:  # Verifica si el código ya ha sido procesado
                    tiempo = datetime.datetime.now()
                    self.codes_seen.add(texto)
                    self.display.insert(END, texto + " -- "+ str(tiempo) +'\n')
                    
                self.draw_rectangle(frm)

    def get_frame(self):
        if self.vid.isOpened():
            verif, frame = self.vid.read()
            if verif:
                self.btnCamera.configure(text="Cerrar cámara")
                self.capta(frame)
                return (verif, cv2.cvtColor(frame, cv2.COLOR_BGR2RGB))
            else:
                messagebox.showwarning("Cámara", """La cámara está siendo utilizada por otra aplicación.
                Ciérrela e inténtelo de nuevo.""")
                self.active_cam()
                return (verif, None)
        else:
            verif = False
            return (verif, None)

    def draw_rectangle(self, frm):
        codes = decode(frm)
        for code in codes:
            data = code.data.decode('ascii')
            x, y, w, h = code.rect.left, code.rect.top, \
                         code.rect.width, code.rect.height
            cv2.rectangle(frm, (x, y), (x + w, y + h), (255, 0, 0), 6)
            cv2.putText(frm, code.type, (x - 10, y - 10), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 50, 255), 2)

    def VideoCaptura(self):
        self.vid = cv2.VideoCapture(self.font_video)
        if self.vid.isOpened():
            self.width = self.vid.get(cv2.CAP_PROP_FRAME_WIDTH)
            self.height = self.vid.get(cv2.CAP_PROP_FRAME_HEIGHT)
            self.canvas.configure(width=self.width, height=self.height)
        else:
            messagebox.showwarning("Cámara no disponible.", "El dispositivo está desactivado o no disponible")
            self.display.delete('1.0', END)
            self.active_camera = False

    def __del__(self):
        if self.active_camera == True:
            self.vid.release()


if __name__ == "__main__":
    App()