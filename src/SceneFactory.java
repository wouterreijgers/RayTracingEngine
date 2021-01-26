import mathematics.MatrixFactory;
import mathematics.Point;
import mathematics.Vector;
import objects.*;
import objects.texture.Color;
import objects.texture.Texture;
import util.Hitinfo;

public class SceneFactory {
    public SceneFactory(){

    }

    public ObjectCol[] getBase(Point eye){
        ObjectCol[] objects = new ObjectCol[6];
        objects[0] = new ObjectCol(
                "CubeBig",
                new Cube(),
                new MatrixFactory().translationMatrix(0, 0, -1)
                        .multiply(new MatrixFactory().rotationMatrix("Z", Math.PI/4))
                        .multiply(new MatrixFactory().scalingMatrix(10, 10, 10)),
                //.multiply(new MatrixFactory().)
                new Hitinfo(),
                eye,
                new Texture(new Color(0.1f, 0.3f, 0.1f), new Vector(0.3, 0.3, 0.3, 0), new Vector(0.2, 0.2, 0.2, 0), 0.9, 0.1001, 0.0, 0.0, 0.0)
        );
        objects[1] = new ObjectCol(
                "Bol 1-mat",
                new Sphere(),
                new MatrixFactory()
                        .scalingMatrix(0.5, 0.5, 0.5)
                        //.multiply(new MatrixFactory().rotationMatrix("Y", Math.PI / 12))
                        .multiply(new MatrixFactory().rotationMatrix("Z", 2*Math.PI))
                        .multiply(new MatrixFactory().translationMatrix(-2, 1, -.5)),
                //						.multiply(new MatrixFactory().rotationMatrix("Y", Math.PI / 4 )),
                new Hitinfo(),
                eye,
                new Texture(new Color("purple"), 0.3,0.2, 2, 0.1, 0.0, 300000.0, 0.0)
        );
        objects[2] = new ObjectCol(
                "Bol 2 spiegel",
                new Sphere(),
                new MatrixFactory().rotationMatrix("Z", 2*Math.PI)
                        .multiply(new MatrixFactory().translationMatrix(-3, -0, -4))
                        .multiply(new MatrixFactory().scalingMatrix(0.5, 0.5, 0.5)),
                //.multiply(new MatrixFactory().)
                new Hitinfo(),
                eye,
                new Texture(new Color("grey"), new Vector(0.4, 0.4, 0.4, 0), new Vector(0.1, 0.1, 0.1, 0), 0.1, 1.0, 0.0, 0.0, 0.0)
        );
        objects[3] = new ObjectCol(
                "Pyramide",
                new Pyramid(),
                new MatrixFactory().translationMatrix(1.5, 0.1, 0)
                        .multiply(new MatrixFactory().rotationMatrix("Z", 2*Math.PI/4))
                        .multiply(new MatrixFactory().scalingMatrix(0.5, 0.5, 0.707)),
                //.multiply(new MatrixFactory().)
                new Hitinfo(),
                eye,
                new Texture(new Color("purple"), 0.2, 0.1, 3.0, 0.05, 0.0, 300000*0.52, 0.01, "circles")
        );
        objects[4] = new ObjectCol(
                "Glass",
                new Sphere(),
                new MatrixFactory().translationMatrix(2, 2, -1)
                        //						.multiply(new MatrixFactory().rotationMatrix("Z", Math.PI/8))
                        .multiply(new MatrixFactory().scalingMatrix(0.5, 0.5, 0.5)),
                new Hitinfo(),
                eye,
                new Texture("glass")
        );
        objects[5] = new ObjectCol(
                "Cube",
                new Cube(),
                new MatrixFactory().rotationMatrix("Z", 0.56*Math.PI/4)
                        .multiply(new MatrixFactory().translationMatrix(-3, -1, 0))
                        .multiply(new MatrixFactory().scalingMatrix(0.5, 0.5, 1)),
                //.multiply(new MatrixFactory().)
                new Hitinfo(),
                eye,
                new Texture(new Color(0.3f, 0.2f, 0.4f), 0.2, 0.3, 2.0, 0.1, 0.0, 0.0, 0.0, "checkboard")        );
        return objects;

    }

    public ObjectCol[] getDesert(Point eye){
        ObjectCol[] objects = new ObjectCol[8];
        objects[0] = new ObjectCol(
                "CubeBig",
                new Cube(),
                new MatrixFactory().translationMatrix(0, 0, -1)
//                        .multiply(new MatrixFactory().rotationMatrix("Z", Math.PI/4))
                        .multiply(new MatrixFactory().scalingMatrix(10, 10, 10)),
                //.multiply(new MatrixFactory().)
                new Hitinfo(),
                eye,
                new Texture(new Color(0.7f, 0.92f, 1f), new Vector(0.2, 0.2, 0.2, 0), new Vector(0.2, 0.2, 0.2, 0), 3.0, 0.001, 0.0, 0.0, 0.0)
        );
        objects[1] = new ObjectCol(
                "Ground",
                new Plane(),
                new MatrixFactory().translationMatrix(0, 0, -.001)
                        .multiply(new MatrixFactory().scalingMatrix(10, 10, 1)),
                new Hitinfo(),
                eye,
                new Texture(new Color("grey"), 0.02,0.2, 2, 0.0, 0.0, 300000.0, 0.0, "desert")
        );
        objects[2] = new ObjectCol(
                "Pyramide 1",
                new Pyramid(),
                new MatrixFactory().rotationMatrix("Z", 2*Math.PI)
                        .multiply(new MatrixFactory().scalingMatrix(0.5, 0.5, 0.707))
                        .multiply(new MatrixFactory().translationMatrix(-4, -2, 0)),
                //.multiply(new MatrixFactory().)
                new Hitinfo(),
                eye,
                new Texture(new Color("white"), new Vector(0.1, 0.1, 0.1, 0), new Vector(0.3, 0.3, 0.3, 0), 0.1, 0.2, 0.0, 0.0, 0.0)
        );
        objects[3] = new ObjectCol(
                "Pyramide 2",
                new Pyramid(),
                new MatrixFactory().rotationMatrix("Z", 2*Math.PI)
                        .multiply(new MatrixFactory().scalingMatrix(0.4, 0.4, 0.5656))
                        .multiply(new MatrixFactory().translationMatrix(-3.5, -1.5, 0)),
                //.multiply(new MatrixFactory().)
                new Hitinfo(),
                eye,
                new Texture(new Color("white"), new Vector(0.1, 0.1, 0.1, 0), new Vector(0.3, 0.3, 0.3, 0), 0.1, 0.2, 0.0, 0.0, 0.0)
        );
        objects[4] = new ObjectCol(
                "Pyramide 1 - top",
                new Pyramid(),
                new MatrixFactory().rotationMatrix("Z", 2*Math.PI)
                        .multiply(new MatrixFactory().scalingMatrix(0.1, 0.1, 0.1414))
                        .multiply(new MatrixFactory().translationMatrix(-4, -2, -0.5657)),
                //.multiply(new MatrixFactory().)
                new Hitinfo(),
                eye,
                new Texture(new Color(0.7725f, 0.701f, 0.345f), new Vector(0.02, 0.02, 0.02, 0), new Vector(0.4, 0.4, 0.4, 0), 3, 0.4, 0.0, 0.0, 0.0)
        );
        objects[5] = new ObjectCol(
                "Pyramide 2",
                new Pyramid(),
                new MatrixFactory().rotationMatrix("Z", 2*Math.PI)
                        .multiply(new MatrixFactory().scalingMatrix(0.1, 0.1, 0.1414))
                        .multiply(new MatrixFactory().translationMatrix(-3.5, -1.5, -0.4243)),
                //.multiply(new MatrixFactory().)
                new Hitinfo(),
                eye,
                new Texture(new Color(0.7725f, 0.701f, 0.345f), new Vector(0.3, 0.3, 0.2, 0), new Vector(0.4, 0.4, 0.4, 0), 3, 0.4, 0.0, 0.0, 0.0)
        );
        objects[6] = new ObjectCol(
                "Pyramide 3",
                new Pyramid(),
                new MatrixFactory().rotationMatrix("Z", 2*Math.PI)
                        .multiply(new MatrixFactory().scalingMatrix(0.3, 0.3, 0.424))
                        .multiply(new MatrixFactory().translationMatrix(-3.1, -2.5, 0)),
                //.multiply(new MatrixFactory().)
                new Hitinfo(),
                eye,
                new Texture(new Color("white"), new Vector(0.1, 0.1, 0.1, 0), new Vector(0.3, 0.3, 0.3, 0), 0.1, 0.2, 0.0, 0.0, 0.0)
        );
        objects[7] = new ObjectCol(
                "Pyramide 3 - top",
                new Pyramid(),
                new MatrixFactory().rotationMatrix("Z", 2*Math.PI)
                        .multiply(new MatrixFactory().scalingMatrix(0.1, 0.1, 0.1414))
                        .multiply(new MatrixFactory().translationMatrix(-3.1, -2.5, -0.2829)),
                new Hitinfo(),
                eye,
                new Texture(new Color(0.7725f, 0.701f, 0.345f), new Vector(0.3, 0.3, 0.2, 0), new Vector(0.4, 0.4, 0.4, 0), 3, 0.4, 0.0, 0.0, 0.0)
        );

        return objects;

    }


    public ObjectCol[] getSpace(Point eye){
        ObjectCol[] objects = new ObjectCol[3];
        objects[0] = new ObjectCol(
                "CubeBig",
                new Cube(),
                new MatrixFactory().translationMatrix(0, 0, -1)
                        .multiply(new MatrixFactory().rotationMatrix("Z", Math.PI/4))
                        .multiply(new MatrixFactory().scalingMatrix(10, 10, 10)),
                //.multiply(new MatrixFactory().)
                new Hitinfo(),
                eye,
                new Texture(new Color(0.f, 0.f, 0.f),0.0, .0, .0, 0.00, 0.0, 0.0, 0.0, "space")
        );
        objects[1] = new ObjectCol(
                "Moon",
                new Plane(),
                new MatrixFactory().rotationMatrix("Y", 0*Math.PI/4)
                        .multiply(new MatrixFactory().translationMatrix(0, 0.2, -0.01))
                        .multiply(new MatrixFactory().scalingMatrix(10, 10, 10)),
                //.multiply(new MatrixFactory().)
                new Hitinfo(),
                eye,
                new Texture(new Color("grey"), .1, .1, 2.1, 0.0, 0.0, 0.0, 0.0, "moon")
        );
        objects[2] = new ObjectCol(
                "Mars",
                new Sphere(),
                new MatrixFactory().rotationMatrix("Z", Math.PI/4)
                        .multiply(new MatrixFactory().translationMatrix(-9, -7, -10.5))
                        .multiply(new MatrixFactory().scalingMatrix(0.07, 0.07, 0.07)),
                //.multiply(new MatrixFactory().)
                new Hitinfo(),
                eye,
                new Texture(new Color("grey"), .2, .2, 2.1, 0.0, 0.0, 0.0, 0.0, "mars")
        );
//        objects[3] = new ObjectCol(
//                "Cube",
//                new Cube(),
//                new MatrixFactory().rotationMatrix("Z", Math.PI/4)
//                        .multiply(new MatrixFactory().scalingMatrix(0.1, 0.1, 0.1))
//                        .multiply(new MatrixFactory().translationMatrix(5, 0.2, -0.5)),
//                //.multiply(new MatrixFactory().)
//                new Hitinfo(),
//                eye,
//                new Texture(new Color(0.7725f, 0.701f, 0.345f), 0.1, 0.4, 2.0, 0.2, 0.0, 300000*0.52, 0.01, "")
//        );

        return objects;

    }

}
