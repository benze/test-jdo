package org.datanucleus.samples.concurrency;

public class Account
{
    private String name;
    private int saldo;

    public Account(String name, int saldo)
    {
        this.name = name;
        this.saldo = saldo;
    }

    public String toString()
    {
        return "Account " + name + " saldo: " + saldo;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String string)
    {
        name = string;
    }

    public int getSaldo()
    {
        return saldo;
    }

    public void setSaldo(int saldo)
    {
        this.saldo = saldo;
    }
}