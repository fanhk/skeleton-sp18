public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    static final double G = 6.67e-11;
//     public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
//         this.xxPos = xxPos;
//         this.yyPos = yyPos;
//         this.xxVel = xxVel;
//         this.yyVel = yyVel;
//         this.mass = mass;
//         this.imgFileName = imgFileName;
//     }
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
//     public Planet(Planet p) {
//         this.xxPos = p.xxPos;
//         this.yyPos = p.yyPos;
//         this.xxVel = p.xxVel;
//         this.yyVel = p.yyVel;
//         this.mass = p.mass;
//         this.imgFileName = p.imgFileName;
//     }
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = xxPos - p.xxPos;
        double dy = yyPos - p.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double distance = calcDistance(p);
        return (G * mass * p.mass) / (distance * distance);
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - xxPos;
        double r = calcDistance(p);
        double F = calcForceExertedBy(p);

        return (F * dx) / r;
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - yyPos;
        double r = calcDistance(p);
        double F = calcForceExertedBy(p);

        return (F * dy) / r;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double F = 0.0;
        for (Planet p : planets) {
            if (this.equals(p)) {
                continue;
            }
            F += calcForceExertedByX(p);
        }
        return F;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double F = 0.0;
        for (Planet p : planets) {
            if (this.equals(p)) {
                continue;
            }
            F += calcForceExertedByY(p);
        }
        return F;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;

        xxVel += dt * aX;
        yyVel += dt * aY;

        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}