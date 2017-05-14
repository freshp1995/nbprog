package com.company.ex4.two;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by patricklanzinger on 12.05.17.
 */
public class UnsafeCachingFactorizer extends GenericServlet implements Servlet {

    private volatile OneValueCache cache = new OneValueCache(null, null);



    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        if (this.cache.getLastNum() != null && i.equals(this.cache.getLastNum())) {
            encodeIntoResponse(resp, this.cache.getFactors());
        } else {
            BigInteger[] factors = factor(i);
            cache = new OneValueCache(i, factors);
            encodeIntoResponse(resp, factors);
        }
    }

    public BigInteger[] factor(BigInteger numbers) {
        BigInteger n = numbers;
        ArrayList<BigInteger> factors = new ArrayList<BigInteger>();
        for (BigInteger i = BigInteger.valueOf(2); i.compareTo(n.divide(i)) >= 0; i.add(BigInteger.ONE)) {
            while (n.mod(i).equals(BigInteger.ZERO)) {
                factors.add(i);
                n = n.divide(i);
            }
        }
        if (n.compareTo(BigInteger.ONE) == 1) {
            factors.add(n);
        }

        BigInteger[] a = new BigInteger[1];

        return factors.toArray(a);
    }

    private void encodeIntoResponse(ServletResponse resp, BigInteger[] bigIntegers) {
        try {

            PrintWriter w = resp.getWriter();
            w.append(Arrays.toString(bigIntegers));
            w.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger(req.getParameter("number"));
    }


    public class OneValueCache {

        private final BigInteger lastNum;
        private final BigInteger[] lastFactors;

        public OneValueCache(BigInteger i, BigInteger[] lastFactors) {
            this.lastNum = i;
            this.lastFactors = lastFactors;
        }

        public BigInteger[] getFactors() {
            return Arrays.copyOf(lastFactors, lastFactors.length);
        }

        public BigInteger getLastNum() {
            return this.lastNum;
        }

    }
}
