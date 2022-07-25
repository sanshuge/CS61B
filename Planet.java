
//ruobing fu

public class Planet {

    public double xxPos;

    public double yyPos;

    public double xxVel;

    public double yyVel;

    public double mass;

    public String imgFileName;

    final double G = 6.67e-11;


    public Planet(double xP, double yP, double xV, double yV, double m, String img)
    {
        xxPos =xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;

    }

    //an identical planet object.
    public Planet (Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass =p.mass;
        imgFileName = p.imgFileName;


    }

    /**
     *
     * @param p
     * @return the distance between the two planets
     */
    public double calcDistance (Planet p
    )
    {
        double distance;
        distance = Math.sqrt(  (xxPos-p.xxPos)* (xxPos-p.xxPos)+ (yyPos-p.yyPos)*(yyPos-p.yyPos));
        return distance;

    }

    /**
     *
     * @param p
     * @return the gravity force exerted by planet p
     */
    public double calcForceExertedBy(Planet p)
    {
        double distance = calcDistance(p);
        double force ;
        force = (G*mass*p.mass)/Math.pow(distance,2);
        return force;


    }
    public double calcForceExertedByX(Planet p){
        double force = calcForceExertedBy(p);
        double forceExertedbyX;
        double distance = calcDistance(p);
        forceExertedbyX = (p.xxPos-xxPos)/distance * force;
        return forceExertedbyX;

    }
    public double calcForceExertedByY(Planet p){
        double force = calcForceExertedBy(p);
        double forceExertedbyY;
        double distance = calcDistance(p);
        forceExertedbyY = (p.yyPos-yyPos)/distance * force;
        return forceExertedbyY;

    }
    public double calcNetForceExertedByX(Planet[] allPlanets){

        double netForceByX = 0;

        for(Planet item  : allPlanets ){
            if(this.equals(item)){continue;}
            netForceByX += calcForceExertedByX(item);
        }
        return netForceByX;

    }
    public double calcNetForceExertedByY(Planet[] allPlanets){

        double netForceByY = 0;

        for(Planet item  : allPlanets ){
            if (this.equals(item)  )
            {
                continue;
            }
            netForceByY += calcForceExertedByY(item);
        }
        return netForceByY;

    }
    public void  update(double dt,double fX, double fY){
        xxVel = xxVel +fX/mass * dt;
        yyVel = yyVel + fY/mass *dt;
        xxPos = xxPos +dt * xxVel;
        yyPos = yyPos + dt * yyVel;


    }
    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }



}
