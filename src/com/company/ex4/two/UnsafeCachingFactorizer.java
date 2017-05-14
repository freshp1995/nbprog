package com.company.ex4.two;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by patricklanzinger on 12.05.17.
 */
public class UnsafeCachingFactorizer extends GenericServlet implements Servlet {

    //the global variables
    //the access to them must be synchronized
    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();

    private synchronized BigInteger getLastNumber() {
        return this.lastNumber.get();
    }

    private synchronized BigInteger[] getLastfactors() {
        return this.lastFactors.get();
    }

    private synchronized void setLastNumber(BigInteger number) {
        this.lastNumber.set(number);
    }

    private synchronized void setLastFactors(BigInteger[] factors) {
        this.lastFactors.set(factors);
    }


    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        if (this.getLastNumber() != null && i.equals(this.getLastNumber())) {
            encodeIntoResponse(resp, this.getLastfactors());
        } else {
            BigInteger[] factors = factor(i);
            this.setLastNumber(i);
            this.setLastFactors(factors);
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


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger(req.getParameter("number"));
    }
}
