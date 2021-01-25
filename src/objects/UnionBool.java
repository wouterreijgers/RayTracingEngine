package objects;

import util.Hitinfo;

public class UnionBool implements ObjectShapeIF {
    public BooleanObj booleanObj;
    public UnionBool(BooleanObj booleanObj){
        this.booleanObj = booleanObj;
    }
    @Override
    public Hitinfo isHit(Ray ray) {
        Hitinfo leftHit = booleanObj.left.isHit(ray);
        Hitinfo rightHit = booleanObj.right.isHit(ray);
//        leftHit.addHit(rightHit.closestT(), rightHit.getPoint(), rightHit.getNormal());
        if(leftHit.getAmountOfHits()>0 && rightHit.getAmountOfHits()>0){
            boolean leftInside = leftHit.isInside;
            boolean rightInside = rightHit.isInside;
            boolean isValid = leftHit.isInside || rightHit.isInside;
            Hitinfo total = new Hitinfo();
            if(leftHit.closestT<=rightHit.closestT){
                leftInside = leftHit.normal.dotproduct(ray.getDirection().getVector()) < 0;
                total.addHit(leftHit.closestT, leftHit.getPoint(), leftHit.getNormal());
            } else {
                rightInside = rightHit.normal.dotproduct(ray.getDirection().getVector()) < 0;
                total.addHit(rightHit.closestT, rightHit.getPoint(), rightHit.getNormal());
            }
            return total;
//            if(isValid != (leftInside || rightInside)){
//                isValid = leftInside || rightInside;
//            }
        }
        else
            return new Hitinfo();
    }
}
