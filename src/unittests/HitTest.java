package unittests;

import mathematics.*;
import objects.Cube;
import objects.ObjectCol;
import objects.Ray;
import objects.Sphere;
import objects.texture.Color;
import objects.texture.Texture;
import org.junit.jupiter.api.Test;
import util.Hitinfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HitTest {

    @Test
    void testCube() {
        Point eye = new Point(7, 0, 0);
        Vector direction =new Vector(-7.0, 0.0, 0.0, 0);
        Ray ray = new Ray();
        ray.setStart(eye);
        ObjectCol object = new ObjectCol(
                new Cube(),
                new MatrixFactory()
                        .scalingMatrix(1, 1, 1)
                        .multiply(new MatrixFactory().translationMatrix(-1.0, 0, 0)),
                //.multiply(new MatrixFactory().rotationMatrix("Z", Math.PI / 3))
                //.multiply(new MatrixFactory().rotationMatrix("Y", Math.PI / 4 )),
                new Hitinfo(),
                eye,
                new Texture(new Color(0.3f, 0.2f, 0.3f), new Vector(0.5, 0.5, 0.5, 0))
        );
        object.inverses();
        object.isHit(direction);
        System.out.println(object.getHitinfo().closestT());
    }

    @Test
    public void test() {
        Sphere sphere = new Sphere();

        Point camLoc = new Point(2, 2, 2);
        Direction direction = new Direction(-1, -1, -1);
        Ray ray = new Ray();
        ray.setDirection(direction);
        ray.setStart(camLoc);

        Hitinfo info = sphere.isHit(ray);
        assert info.closestT() == 1.4226497308103736;

        Point expectedHitPoint = new Point(
                0.5773502691896264,
                0.5773502691896264,
                0.5773502691896264
        );
    }




}

