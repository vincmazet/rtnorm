// RPNORM    Random numbers from the positive normal distribution.
//   RPNORM(N,m,s) is an N-by-1 vector with random entries, generated
//   from a positive normal distribution with mean m and standard
//   deviation s.
//
// (c) Vincent Mazet, 11/01/2005
// Centre de Recherche en Automatique de Nancy, France
// vincent.mazet@cran.uhp-nancy.fr
//
// References:
// * V. Mazet, D. Brie, J. Idier, "Simulation of Positive Normal Variables
//   using several Proposal Distributions", IEEE Workshop Statistical
//   Signal Processing 2005, july 17-20 2005, Bordeaux, France.
// * V. Mazet, D. Brie, J. Idier, "Simuler une distribution normale à support
//   positif à partir de plusieurs lois candidates", GRETSI 2005,
//   september 6-9, 2005, Louvain-la-Neuve, Belgium.

function x = rpnorm(N,m,s) 

if s<0  then  error('Standard deviation must be positive.');  end;
if N<=0 then  error('N is wrong.');                           end;

x = [];       // Output vector
NN = N;

// Intersections
A  = 1.136717791056118;
mA = (1-A^2)/A*s;
mC = s * sqrt(%pi/2);

while length(x)<NN,
  
  if m < mA,      // 4. Exponential distribution
      
      a = (-m + sqrt(m^2+4*s^2)) / 2 / s^2;
      z = -log(1-grand(N,1,'def'))/a;
      rho = exp( -(z-m).^2/2/s^2 - a*(m-z+a*s^2/2) );
	
	elseif m <= 0,   // 3. Normal distribution truncated at the mean
	                 // equality because 3 is faster to compute than 2
      
      z = abs(grand(N,'mn',0,s^2))'+m;
      rho = 1*(z>=0);
	
	elseif m < mC,   // 2. Normal distribution coupled with the uniform one
	   
      r = (grand(N,1,'def') < m/(m+sqrt(%pi/2)*s));
      u = grand(N,1,'def')*m;
      g = abs(grand(N,'mn',0,s^2))'+m;
      z = r.*u + (~r).*g;
      rho = r.*exp(-(z-m).^2/2/s^2) + (~r).*ones(N,1);
  	   
	else,            // 1. Normal distribution
      
      z = grand(N,'mn',m,s^2)';
      rho = 1*(z>=0);
      
  end;
  
  //	Accept/reject the propositions
  reject = (grand(N,1,'def') > rho);
  z(reject) = [];
  if (length(z)>0) then x = [x ; z]; end;
  N = N - length(z);
  
end;

endfunction;

