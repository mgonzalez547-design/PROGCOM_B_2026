import time

def reproducir_baile():
    print("--- INICIO DE LA COREOGRAFÍA (See Tình) ---")
    
    # Intro: Saludo y manos a la cara
    paso_saludo()
    paso_olfato()
    
    # El "Tình Tình Tình" (Movimiento de manos circular)
    paso_corazon_circular()
    
    # El estribillo principal
    paso_boxeo_ritmico()
    paso_paz_y_vueltita()
    
    # El final con estilo
    paso_pose_final()
    
    print("\n--- ¡Misión cumplida! Check Solution ---")

def paso_saludo():
    print("[0:01] Mano derecha a la frente tipo saludo militar.")
    time.sleep(1)

def paso_olfato():
    print("[0:03] Movimiento de manos cerca de la nariz, como oliendo algo dulce.")
    time.sleep(1)

def paso_corazon_circular():
    print("[0:05] Cruza las manos frente al pecho y muévelas en círculos rítmicos.")
    print("       'Tình tình tình tang tang tính...'")
    time.sleep(2)

def paso_boxeo_ritmico():
    print("[0:08] Estira los brazos hacia adelante alternando puños (derecha, izquierda).")
    time.sleep(1.5)

def paso_paz_y_vueltita():
    print("[0:11] Señal de 'V' (paz) con los dedos y un pequeño salto/giro de hombros.")
    time.sleep(1.5)

def paso_pose_final():
    print("[0:18] Manos a la nuca, movimiento de cadera y sonrisa a la cámara.")
    time.sleep(1)

# Ejecutar el baile
reproducir_baile()
