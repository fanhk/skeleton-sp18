public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public final static double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
	this.xxPos = xP;
	this.yyPos = yP;
	this.xxVel = xV;
	this.yyVel = yV;
	this.mass = m;
	this.imgFileName = img;
    }

    public Planet(Planet p) {
	this.xxPos = p.xxPos;
	this.yyPos = p.yyPos;
	this.xxVel = p.xxVel;
	this.yyVel = p.yyVel;
	this.mass = p.mass;
	this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
	double dx = (this.xxPos - p.xxPos) * (this.xxPos - p.xxPos);
	double dy = (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos);
	return Math.sqrt(dx + dy);
    }

    public double calcForceExertedBy(Planet p) {
	double r = this.calcDistance(p);
	return (Planet.G * this.mass * p.mass) / (r * r); 
    }

    public double calcForceExertedByX(Planet p) {
	double dx = this.xxPos - p.xxPos;
	dx = dx > 0 ? dx : -dx;
	return this.calcForceExertedBy(p) * dx / this.calcDistance(p);
    }
    
    public double calcForceExertedByY(Planet p) {
	double dy = this.yyPos - p.yyPos;
	dy = dy > 0 ? dy : -dy;
	return this.calcForceExertedBy(p) * dy / this.calcDistance(p);
    }
    
    public double calcNetForceExertedByX(Planet[] pArr) {
	double netForceX = 0.0;
	for (Planet p : pArr) {
	    if (p.equals(this)) {
		continue;
	    }
	    netForceX += this.calcForceExertedByX(p);
	}
	return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] pArr) {
	double netForceY = 0.0;
	for (Planet p : pArr) {
	    if (p.equals(this)) {
		continue;
	    }
	    netForceY += this.calcForceExertedByY(p);
	}
	return netForceY;
    }
}
