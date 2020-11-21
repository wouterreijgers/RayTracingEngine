package objects;

import util.Hitinfo;

interface ObjectShapeIF {

    /*
    This functon calculates if there is a hit between the ray and the object.
    If so, it will return a 'hitinfo' instance.
     */
    public Hitinfo isHit(Ray ray);


}
