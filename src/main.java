import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class main {
    public static class pair{
        pair(BigInteger x,BigInteger y){
            a = x;
            b = y;
        }
        BigInteger a;
        BigInteger b;
    }
    public static pair extended_gcd(BigInteger a,BigInteger b){
        BigInteger x = BigInteger.ZERO;
        BigInteger lastx = BigInteger.ONE;
        BigInteger y = BigInteger.ONE;
        BigInteger lasty = BigInteger.ZERO;
        while (!b.equals(BigInteger.ZERO))
        {
            BigInteger[] quotientAndRemainder = a.divideAndRemainder(b);
            BigInteger quotient = quotientAndRemainder[0];

            BigInteger temp = a;
            a = b;
            b = quotientAndRemainder[1];

            temp = x;
            x = lastx.subtract(quotient.multiply(x));
            lastx = temp;

            temp = y;
            y = lasty.subtract(quotient.multiply(y));
            lasty = temp;
        }

        pair result = new pair(lastx,lasty);
        return result;
    }

    public static void main(String[] args){
        BigInteger e1,e2,c1,c2,n,m;
        n = new BigInteger("150678335266861095002572723333421282220409365403957911693254396128730617107876940427467997767542038044913123014473194398965794326151569997992931441738222601354311101683321094039646708810049080352318664122528864573770534457310597395116415140435628248113573610594600747069361688543457436718507679940122303782771");
        e1 = new BigInteger("107166534028487606052501701171794662287863298798213301291868023513001038658468339387388250268128191458540561893628171293158546566406176485838269833391318913567937139949589278100631569084053090441273919550903375558895832292114758849686983382621864140368601140619779758499357801626271608550693353047963879305653");
        e2 = new BigInteger("32589850930756936422283620020569406081234429378640908517872408695592831592290537807808428635670403091648306105929767728829586233960076991471575814113836423077427437201289244915836320812815348657757072325170137054014258959224745585140089503033541162918471030499346164379764439415857918668350978268239040789429");
        c1 = new BigInteger("147754449896234167140059503125270557905714410907408818643308010712944510319457050553893079855838817753871456510764455380116222279163031874419651593588972740427911942306265084405152248748778307537885658554705574727955748286950322994341294726623957360834790698348921684849915038258288468980127346016807389898711");
        c2 = new BigInteger("72279748436098447061383611342693064104130790516971041469548184150886546730480287131386233506683104236269034323518496198183701696883382137180545926686551934237286777368152076529594564229974519786937336526859830603017158058431546963896451784252561625148594313485340572761838238740908104236922972181548166426779");

        if (e1.gcd(e2).equals(BigInteger.ONE)){
            System.out.println("gcd(e1,e2) = 1 so we (and David) can perform Common Modulus Attack.\n");
        }

        pair result = extended_gcd(e1,e2);
        result.a = result.a.abs();
        result.b = result.b.abs();
        BigInteger c2_inverse = extended_gcd(n,c2).b;
        m = (c1.modPow(result.a,n).multiply(c2_inverse.modPow(result.b,n))).mod(n);
        if (m.modPow(e1,n).equals(c1) && m.modPow(e2,n).equals(c2)){
            System.out.println("Message is:\n");
            System.out.println(m);
        }
    }
}
