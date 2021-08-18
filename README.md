# Simulation of truncated Gaussian Distribution

In 2005, we developped a first method to generate random variables distributed from a Gaussian distribution defined on a semi-finite interval [a,+∞[. This method was implemented in Scilab, Matlab and Java.

Following the [article](http://dx.doi.org/10.1007/s11222-009-9168-1) published in 2011 by Nicolas Chopin, we have developped a method to simulate a Gaussian distribution defined on a finite interval [a,b]. This method is still able to consider semi-finite interval by setting b=+∞. The principle is to divide the interval into regions with the same area where acceptation-reject algorithms with appropriate distributions are used.

Nicolas Chopin's methodis coded in C, but only on a semi-finite interval [a,+∞[. We extend his method to a finite interval [a,b], following its recommandations. The method is implemented in Matlab and C++; it is faster than our former implementation and also allows to consider a finite interval. However, it is still not able to generate a random vector, contrary to the version of 2005.

The following table summarize the implementations and their characteristics, as well as similar implementations in other languages :

| Code | Author | Year | Size | Language | Dimensions | Truncation Interval |
| ---- | ------ | ---- | ---- | -------- | ---------- | ------------------- |
| [truncgauss](http://chopin.perso.math.cnrs.fr/truncgauss.tgz) | [N. Chopin](https://nchopin.github.io/) | 2011 | 462 kb | C/Python | 1/2 | semi-finite/semi-finite or finite |
| [rpnorm](rpnorm.zip) | V. Mazet           | 2005 |   2 kb | Matlab | 1 or greater | semi-finite |
| [rpnorm](rpnorm.sci) | V. Mazet           | 2005 |   2 kb | Scilab | 1 or greater | semi-finite |
| [rpnorm](PositiveGaussian.java) | B. Perret, V. Mazet           | 2010 |   4 kb | Java | 1 or greater | semi-finite |
| [rtnorm](rtnormM.zip) | V. Mazet           | 2012 |  58 kb | Matlab | 1 | semi-finite or finite |
| [rtnorm](rtnormCpp.zip) | G. Dollé, V. Mazet | 2012 |  67 kb | C++ | 1 | semi-finite or finite |
| [rtnorm](https://github.com/demichie/MrLavaLoba/blob/master/rtnorm.py) | C. Lassner         | 2013 |  60 kb | Python | 1 | semi-finite or finite |
| [dtnorm](https://github.com/alanrogers/dtnorm) | [Alan R. Rogers](http://content.csbs.utah.edu/~rogers/) | 2016 | 232 kb | C | 1 | finite |

I did not compare theses implementations with the [truncnorm](https://docs.scipy.org/doc/scipy/reference/generated/scipy.stats.truncnorm.html#scipy-stats-truncnorm) function in Scipy.

## Documents and codes

* N. Chopin, « [Fast simulation of truncated Gaussian distributions](http://dx.doi.org/10.1007/s11222-009-9168-1) », Statistics and Computing 21, 2011.
* V. Mazet, « [Simulation d'une distribution gaussienne tronquée sur un intervalle fini](rtnorm.pdf) », technical report, Université de Strasbourg/CNRS, 2012

