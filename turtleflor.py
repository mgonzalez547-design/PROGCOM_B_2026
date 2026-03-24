import turtle

screen = turtle.Screen()
screen.setup(width=600, height=600)
screen.bgcolor("#99D9EA") 
t = turtle.Turtle()
t.speed(0)
t.pensize(3)

def draw_filled_circle(x, y, radius, color):
    t.penup()
    t.goto(x, y - radius)
    t.setheading(0)
    t.pendown()
    t.fillcolor(color)
    t.begin_fill()
    t.circle(radius)
    t.end_fill()

def draw_flower(x, y, petal_color, size=18): # Slightly smaller flowers for the smaller cone
    t.fillcolor(petal_color)
    for i in range(5):
        t.penup()
        t.goto(x, y)
        t.setheading(i * 72 + 15) 
        t.forward(size * 0.8)
        t.pendown()
        t.begin_fill()
        t.circle(size * 0.75)
        t.end_fill()
    draw_filled_circle(x, y, size * 0.5, "#FFD700")

def draw_leaf(x, y, angle):
    t.penup()
    t.goto(x, y)
    t.setheading(angle)
    t.fillcolor("#32CD32")
    t.pendown()
    t.begin_fill()
    t.circle(40, 90) # Smaller leaves
    t.left(90)
    t.circle(40, 90)
    t.end_fill()

def draw_wrapper():
    """Draws a smaller, more compact cone."""
    t.penup()
    t.goto(0, -100) # Moved up from -180
    t.pendown()
    t.fillcolor("#FFF2CC")
    t.begin_fill()
    t.goto(90, 80)  # Narrower (90) and shorter (80)
    t.setheading(145)
    t.circle(160, 70) # Tighter curve for the top
    t.goto(0, -100) 
    t.end_fill()

def draw_bow():
    cx, cy = 0, -75 # Moved up to match the new cone base
    t.penup()
    t.goto(cx, cy)
    t.fillcolor("#FF66B2")
    t.begin_fill()
    t.goto(cx + 50, cy + 35)
    t.goto(cx + 50, cy - 35)
    t.goto(cx, cy)
    t.goto(cx - 50, cy + 35)
    t.goto(cx - 50, cy - 35)
    t.goto(cx, cy)
    t.end_fill()
    draw_filled_circle(cx, cy, 15, "#FF66B2")

# --- DRAWING ---

draw_wrapper()

# Leaves adjusted for the smaller height
draw_leaf(-70, 70, 130)  
draw_leaf(65, 75, 50)    

# Flowers shifted down to fit the smaller cone (Lower Y values)
draw_flower(-50, 80, "#FF8A8A", 20)  
draw_flower(0, 95, "#33CCFF", 20)    
draw_flower(55, 85, "#FF9900", 20)   
draw_flower(-30, 40, "#FFB6C1", 18)  
draw_flower(30, 45, "#FFFFFF", 18)   
draw_flower(0, 10, "#C080FF", 18)     

draw_bow()

t.hideturtle()
screen.mainloop()