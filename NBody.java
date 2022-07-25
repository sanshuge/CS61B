//simulation of the planets in the solar system.
// ruobing fu

public class NBody {
    public static double readRadius (String filename){
        In in  = new In(filename);

        int numP = in.readInt();
        double radius = in.readDouble();
        return radius;

    }
    public static Planet[] readPlanets (String filename){

        In in = new In(filename);
        int numP = in.readInt();
        double radius = in.readDouble();
        Planet[] Bodies = new Planet[numP];
        for(int i = 0;i<numP; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Bodies[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);

        }

        return Bodies;

    }

    public static void main(String[] args) {

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        // draw the planets in data/planets on the starfield.
        Planet[] Bodies = readPlanets("data/planets.txt");
        double radius = readRadius("data/planets.txt");

        StdDraw.setXscale(-radius,radius); // the radius of the universe
        StdDraw.setYscale(-radius,radius);




        StdDraw.enableDoubleBuffering();
        double time=0;
        int numP = Bodies.length;

        while (time < T){
            double[] xForces = new double[numP];
            double[] yForces = new double[numP];

            for (int i =0;i<numP;i++){
                xForces[i] = Bodies[i].calcNetForceExertedByX(Bodies);

            }
            for (int i =0;i<numP;i++){
                yForces[i] = Bodies[i].calcNetForceExertedByY(Bodies);

            }
            // update
            for (int i=0; i<numP;i++){
                Bodies[i].update(dt,xForces[i],yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (int i =0;i<numP;i++){
            Bodies[i].draw();;
        }

            StdDraw.show();

            time = time +dt;
            StdDraw.pause(10);

            StdOut.printf("%d\n", Bodies.length);
            StdOut.printf("%.2e\n", radius);
            for (int i = 0; i < Bodies.length; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        Bodies[i].xxPos, Bodies[i].yyPos, Bodies[i].xxVel,
                        Bodies[i].yyVel, Bodies[i].mass, Bodies[i].imgFileName);
            }



        }



    }
}


