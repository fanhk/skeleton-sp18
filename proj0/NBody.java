public class NBody {
  public static double readRadius(String path) {
    In in = new In(path);
    int numberOfPlanets = in.readInt();
    return in.readDouble();
  }

  public static Planet[] readPlanets(String path) {
    In in = new In(path);
    
    int numberOfPlanets = in.readInt();
    double radius = in.readDouble();

    Planet[] planets = new Planet[numberOfPlanets];
    for (int i = 0; i < numberOfPlanets; i++) {
      double xP = in.readDouble();
			double yP = in.readDouble();
			double vX = in.readDouble();
			double vY = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();
			planets[i] = new Planet(xP, yP, vX, vY, mass, img);
    }

    return planets;
  }

  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double radius = readRadius(filename);
    Planet[] planets = readPlanets(filename);
    int n = planets.length;

    StdDraw.enableDoubleBuffering();

    StdDraw.setScale(-radius, radius);
    StdDraw.picture(0, 0, "images/starfield.jpg");
    for (Planet planet : planets) {
        planet.draw();
    }

    for (double time = 0; time < T; time += dt) {
        double[] xForces = new double[n];
        double[] yForces = new double[n];
        for (int i = 0; i < n; i++) {
            xForces[i] = planets[i].calcNetForceExertedByX(planets);
            yForces[i] = planets[i].calcNetForceExertedByY(planets);
        }
        for (int i = 0; i < n; i++)
            planets[i].update(dt, xForces[i], yForces[i]);

        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Planet planet : planets) {
            planet.draw();
        }
        StdDraw.show();
        StdDraw.pause(10);
    }

    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", planets[i].xxPos, planets[i].yyPos,
                planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }
  }
}
