import math

def calcular_eoq():
    print("--- Optimizador de Inventarios (EOQ) ---")
    
    try:
        # Ingreso de variables industriales
        demanda_anual = float(input("Ingrese la Demanda Anual (unidades): "))
        costo_pedido = float(input("Ingrese el costo por realizar cada pedido ($): "))
        costo_mantenimiento = float(input("Ingrese el costo de mantener una unidad al año ($): "))

        if demanda_anual <= 0 or costo_pedido <= 0 or costo_mantenimiento <= 0:
            print("Error: Los valores deben ser mayores a cero.")
            return

        # Lógica de cálculo (Fórmula de Wilson)
        q_optimo = math.sqrt((2 * demanda_anual * costo_pedido) / costo_mantenimiento)
        
        # Cálculos adicionales de operación
        pedidos_al_anio = demanda_anual / q_optimo
        costo_total_inventario = (demanda_anual / q_optimo * costo_pedido) + (q_optimo / 2 * costo_mantenimiento)

        # Salida de resultados
        print("\n" + "="*30)
        print("RESULTADOS DE LA OPTIMIZACIÓN")
        print("="*30)
        print(f"Lote Económico de Pedido (Q*): {round(q_optimo, 2)} unidades")
        print(f"Número de pedidos al año: {round(pedidos_al_anio, 2)}")
        print(f"Costo Total Anual de Gestión: ${round(costo_total_inventario, 2)}")
        print("="*30)

    except ValueError:
        print("Error: Por favor, ingrese solo números válidos.")

if __name__ == "__main__":
    calcular_eoq()
