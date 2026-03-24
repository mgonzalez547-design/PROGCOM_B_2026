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

def draw_flower(x, y, petal_color, size=22):


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
    t.circle(50, 90)
    t.left(90)
    t.circle(50, 90)
    t.end_fill()

def draw_wrapper():
    """Draws the tan cone-shaped wrapper."""
    t.penup()
    t.goto(0, -180) 
    t.pendown()
    t.fillcolor("#FFF2CC")
    t.begin_fill()
    t.goto(130, 150) 
    t.setheading(145)
    t.circle(230, 70)
    t.goto(0, -180) 
    t.end_fill()

def draw_bow():
    cx, cy = 0, -140 
    t.penup()
    t.goto(cx, cy)
    t.fillcolor("#FF66B2")
    
    t.begin_fill()
    t.goto(cx + 65, cy + 45)
    t.goto(cx + 65, cy - 45)
    t.goto(cx, cy)
    t.goto(cx - 65, cy + 45)
    t.goto(cx - 65, cy - 45)
    t.goto(cx, cy)
    t.end_fill()
    
    draw_filled_circle(cx, cy, 20, "#FF66B2")


draw_wrapper()


draw_leaf(-95, 140, 130) 
draw_leaf(-120, 70, 160) 
draw_leaf(85, 145, 50) 


draw_flower(-70, 145, "#FF8A8A", 24)
draw_flower(0, 160, "#33CCFF", 24)   
draw_flower(75, 150, "#FF9900", 24)   


draw_flower(-40, 90, "#FFB6C1", 22)
draw_flower(40, 100, "#FFFFFF", 22)
draw_flower(-90, 80, "#FFDAB9", 22)
draw_flower(100, 90, "#FF8C00", 20)

draw_flower(0, 45, "#C080FF", 22)
draw_flower(-50, 30, "#FFD1DC", 20)
draw_flower(50, 25, "#AFEEEE", 20)


draw_bow()

t.hideturtle()
screen.mainloop()
