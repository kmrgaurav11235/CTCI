# A Useful Operation: (n & (n - 1)) == 0
## Question: Explain what the above operation does?
We can work backwards to solve this.

## What does it mean if (A & B) == 0?
It means that A and B do not have a 1-bit at the same place. So, if (n & (n - 1)) == 0, then n and (n - 1) never share a 1.

## What does (n - 1) looks like when compared with n?
Subtracting 1 from a number:

```
1101011000 
       - 1 
----------
1101010111
```

When we subtract 1 from a number, we look at its Least Significant Bit (LSB). 
* If it is a 1, we change it to 0 and we are done. 
* If it is a 0, we must 'borrow' from a larger bit. So, we go to increasingly larger bits changing each 0 to 1, until we encounter a 1. We flip this 1 to 0 and we are done.

Thus, (n - 1) will look like n except that n's initial 0s will be changed to 1s and n's Least Significant 1 will be changed to 0. So:
```
If n = abcde1000
then n - 1 = abcde0111
```
## So what does (n & (n - 1)) == 0 indicate?
n and (n - 1) must have no 1s in common. Given that they look like this:
```
If n = abcde1000
then n - 1 = abcde0111
```
abcde must be all 0s. This means that n looks like this: ```00..00100..00```. The value of n is, therefore, a power of 2.

One more possible value of n is 0, where no 1 exists. 

So, in conclusion:
* ```(n & (n - 1)) == 0``` can be used to check it n is either 0 or a power of 2.
