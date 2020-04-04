import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AminoAcidLLTester {

    @Test // tests what would happen when null amino acids are created //
    public void AminoAcidLLTest1() {
        // making objects //
        AminoAcidLL x = new AminoAcidLL();
        AminoAcidLL y = new AminoAcidLL();

        // sorting //
        AminoAcidLL.sort(x);
        AminoAcidLL.sort(y);

        // check sorting //
        assertTrue(x.isSorted());
        assertTrue(y.isSorted());

        // initializing //
        char xChar[], yChar[];
        int xCounts[], yCounts[];

        // filling in //
        xChar = x.aminoAcidList();
        yChar = y.aminoAcidList();
        xCounts = x.aminoAcidCounts();
        yCounts = y.aminoAcidCounts();

        // tests //
        assertArrayEquals(xChar, yChar);
        assertArrayEquals(xCounts, yCounts);
    }

    @Test // tests with one codon //
    public void AminoAcidLLTest2() {
        // making objects //
        AminoAcidLL x = new AminoAcidLL("AGA");
        AminoAcidLL y = new AminoAcidLL("AGA");

        // sorting //
        AminoAcidLL.sort(x);
        AminoAcidLL.sort(y);

        // check sorting //
        assertTrue(x.isSorted());
        assertTrue(y.isSorted());

        // initializing //
        char xChar[], yChar[];
        int xCounts[], yCounts[];

        // filling in //
        xChar = x.aminoAcidList();
        yChar = y.aminoAcidList();
        xCounts = x.aminoAcidCounts();
        yCounts = y.aminoAcidCounts();

        // tests //
        assertArrayEquals(xChar, yChar);
        assertArrayEquals(xCounts, yCounts);
        assertEquals(0, x.aminoAcidCompare(y));
        assertEquals(0, x.codonCompare(y));
    }

    @Test // tests when a codon is < 3 //
    public void AminoAcidLLTest3() {
        // making objects //
        AminoAcidLL x = new AminoAcidLL("GC");
        AminoAcidLL y = new AminoAcidLL("GC");

        // sorting //
        AminoAcidLL.sort(x);
        AminoAcidLL.sort(y);

        // check sorting //
        assertTrue(x.isSorted());
        assertTrue(y.isSorted());

        // initializing //
        char xChar[], yChar[];
        int xCounts[], yCounts[];

        // filling in //
        xChar = x.aminoAcidList();
        yChar = y.aminoAcidList();
        xCounts = x.aminoAcidCounts();
        yCounts = y.aminoAcidCounts();

        // tests //
        assertArrayEquals(xChar, yChar);  //Making sure both xChar arrays are equal
        assertArrayEquals(xCounts, yCounts);  //Making sure the counts arrays are equal
        assertEquals(0, x.aminoAcidCompare(y));
        assertEquals(0, x.codonCompare(y)); //Using assertEquals for methods aminoAcidCompare/codonCompare
    }

    @Test // tests FromRNASequence when null //
    public void AminoAcidLLTest4() {
        // declaring objects //
        AminoAcidLL x;
        AminoAcidLL y;

        // initializing objects //
        x = AminoAcidLL.createFromRNASequence("");
        y = AminoAcidLL.createFromRNASequence("");

        // sorting //
        AminoAcidLL.sort(x);
        AminoAcidLL.sort(y);

        // tests //
        assertNull(x);
        assertNull(y);
    }

    @Test // tests FromRNASequence with a normal codon //
    public void AminoAcidLLTest5() {
        // declaring objects //
        AminoAcidLL x;
        AminoAcidLL y;

        //initializing objects //
        x = AminoAcidLL.createFromRNASequence("GCG");
        y = AminoAcidLL.createFromRNASequence("GCG");

        // sorting //
        AminoAcidLL.sort(x);
        AminoAcidLL.sort(y);

        // check sorting //
        assertTrue(x.isSorted());
        assertTrue(y.isSorted());

        // initializing //
        char xChar[], yChar[];
        int xCounts[], yCounts[];

        // filling in //
        xChar = x.aminoAcidList();
        yChar = y.aminoAcidList();
        xCounts = x.aminoAcidCounts(); //Setting the values for the arrays that were initiated with counts and codons
        yCounts = y.aminoAcidCounts();

        // tests //
        assertArrayEquals(xChar, yChar);
        assertArrayEquals(xCounts, yCounts);
        assertEquals(0, x.aminoAcidCompare(y));
        assertEquals(0, x.codonCompare(y));
    }

    // tests FromRNASequence with a codon < 2 //
    @Test
    public void AminoAcidLLTest6(){
        // declaring objects //
        AminoAcidLL x;
        AminoAcidLL y;

        // initializing objects //
        x = AminoAcidLL.createFromRNASequence("AU");
        y = AminoAcidLL.createFromRNASequence("AU");

        // sorting //
        AminoAcidLL.sort(x);
        AminoAcidLL.sort(y);

        // tests //
        assertNull(x);
        assertNull(y);
    }
}
