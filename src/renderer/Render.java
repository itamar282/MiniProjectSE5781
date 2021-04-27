package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.MissingResourceException;

public class Render {
    ImageWriter _imageWriter;
    Scene _scene;
    Camera _camera;
    RayTracerBase _rayTracerBase;

    public Render setImageWriter(ImageWriter imageWriter) {
        _imageWriter = imageWriter;
        return this;
    }

    public Render setScene(Scene scene) {
        _scene = scene;
        return this;
    }

    public Render setCamera(Camera camera) {
        _camera = camera;
        return this;
    }

    public Render setRayTracerBase(RayTracerBase rayTracerBase) {
        _rayTracerBase = rayTracerBase;
        return this;
    }

    public void renderImage() {
        try {
            if (_imageWriter == null) {
                throw new MissingResourceException("missing resource", _imageWriter.getClass().getName(), "");
            }
            if (_scene == null) {
                throw new MissingResourceException("missing resource", _scene.getClass().getName(), "");
            }
            if (_camera == null) {
                throw new MissingResourceException("missing resource", _camera.getClass().getName(), "");
            }
            if (_rayTracerBase == null) {
                throw new MissingResourceException("missing resource", _rayTracerBase.getClass().getName(), "");
            }
            //rendering the image
            int nX = _imageWriter.getNx();
            int nY = _imageWriter.getNy();
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    Ray ray = _camera.constructRayThroughPixel(nX, nY, j, i);
                    Color pixelColor = _rayTracerBase.traceRay(ray);
                    _imageWriter.writePixel(j, i, pixelColor);
                }
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
    }

    public void printGrid(int interval, Color color) {
        if (_imageWriter == null) {
            throw new MissingResourceException("missing resource", _imageWriter.getClass().getName(), "");
        }

        int nX = _imageWriter.getNx();
        int nY = _imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    _imageWriter.writePixel(j, i, Color.BLACK);
                }
            }
        }
    }

    public void writeToImage() {
        if (_imageWriter == null) {
            throw new MissingResourceException("missing resource", _imageWriter.getClass().getName(), "");
        }

        _imageWriter.writeToImage();
    }
}
