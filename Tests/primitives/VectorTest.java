package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Unit tests for primitives.Vector class
 *
 * @author Itamar
 */
class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

    @Test
    void ZeroVectorTest() {
        try { // test zero vector
            new Vector(0, 0, 0);
            out.println("ERROR: zero vector does not throw an exception");
        } catch (Exception e) {
        }
    }

    @Test
    void add() {
        /////////// Testing the adding function//////////////
        assertEquals(v1, v2.add(new Vector(3, 6, 9)), "ERROR: adding function not working well");
    }

    @Test
    void subtract() {
        /////////// Testing the subtracting function//////////////
        assertEquals(v1.subtract(new Vector(3, 6, 9)), v2, "ERROR: subtracting function not working well");
    }

    @Test
    void scale() {
        /////////// Testing the scale function//////////////
        assertEquals(v1.scale(10), v2.scale(-5), "ERROR: scale function not working well");
    }

    @Test
    void dotProduct() {
        /////////// Testing the scalar multiplication//////////////
        assertEquals(
                v1.dotProduct(v3),
                v1.dotProduct(v2) + 28,
                "ERROR: dotProduct() for orthogonal vectors is not zero");
    }

    @Test
    public void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v3);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v3.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v3)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-product of co-lined vectors
        assertThrows(
                IllegalArgumentException.class, () -> v1.crossProduct(v2), "crossProduct() for parallel vectors does not throw an exception");
        // try {
        //     v1.crossProduct(v2);
        //     fail("crossProduct() for parallel vectors does not throw an exception");
        // } catch (Exception e) {}
    }


    @Test
    void lengthSquared() {
        /////////// Testing the lengthSquared function//////////////
        assertTrue(isZero(v1.lengthSquared() - 14), "ERROR: lengthSquared() wrong value");
    }

    @Test
    void Length() {
        /////////// Testing the length function//////////////
        assertTrue(isZero(new Vector(0, 3, 4).length() - 5), "ERROR: length() wrong value");
    }

    @Test
    void normalize() {
        /////////// Testing the normalize function//////////////
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v.getHead());
        Vector vCopyNormalize = vCopy.normalize();

        //Test 1 : Testing if the normalize function working properly//////////////
        assertEquals(vCopy, vCopyNormalize, "ERROR: normalize() function creates a new vector");

        //Test 2 : Testing whether the normalize function returns the unit vector//////////////
        assertTrue(isZero(vCopyNormalize.length() - 1), "ERROR: normalize() result is not a unit vector");
    }

    @Test
    void normalized() {
        /////////// Testing whether the normalized function return the same object or a new object//////////////
        Vector u = v1.normalized();
        assertNotEquals(u, v1, "ERROR: normalize() result is not a unit vector");
    }

}