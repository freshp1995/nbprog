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
    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();


    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        if (i.equals(lastNumber.get())) {
            encodeIntoResponse(resp, lastFactors.get());
        } else {
            BigInteger[] factors = factor(i);
            lastNumber.set(i);
            lastFactors.set(factors);
            encodeIntoResponse(resp, factors);
        }
    }

    public BigInteger[] factor(BigInteger numbers) {
        BigInteger n = numbers;
        List<BigInteger> factors = new ArrayList<BigInteger>();
        for (BigInteger i = BigInteger.valueOf(2); i.compareTo(n.divide(i)) >= 0; i.add(BigInteger.ONE)) {
            while (n.mod(i).equals(BigInteger.ZERO)) {
                factors.add(i);
                n = n.divide(i);
            }
        }
        if (n.compareTo(BigInteger.ONE) == 1) {
            factors.add(n);
        }
        return (BigInteger[]) factors.toArray();
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
        System.out.println(req.toString());
        return new BigInteger("123");
    }
}
