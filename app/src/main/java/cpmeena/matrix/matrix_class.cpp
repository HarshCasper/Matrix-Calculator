//
// Created by CPMeena on 08-01-2018.
//

#include<iostream>
#include<stdlib.h>					//use for rand function;

class matrix{
public:
	matrix(int nn=1,int mm=1);          										//normal constructor
	matrix(const matrix&);														//copy constructor
	matrix& operator = (const matrix&);											//copy assignment operator
	~matrix();																	//destructor

	void get();																	//insert random element
	void show()const;															//display matrix
	friend std::ostream& operator<<(std::ostream&,const matrix&);
	friend std::istream& operator>>(std::istream&,matrix&);

	matrix &operator+=(const matrix&);											// +=
	matrix &operator-=(const matrix&);											// -=
	matrix &operator*=(const matrix&);											// *=
	matrix &operator/=(const matrix&);											// /=

//	matrix &operator+=(const double&);											//doesn't exit
//	matrix &operator-=(const double&);											//doesn't exit
	matrix &operator*=(const double&);
	matrix &operator/=(const double&);

	matrix operator+(const matrix&)const;										// +
	matrix operator-(const matrix&)const;										// -
	matrix operator*(const matrix&)const;										// *	(matrix * matrix)
	matrix operator/(const matrix&)const;										// /	(matirx / matrix)

//	matrix operator+(const double&)const;										//doesn't exit
//	matrix operator-(const double&)const;										//doesn't exit
	matrix operator*(const double&)const;
	matrix operator/(const double&)const;

	//void matrix::add(const matrix& y){*this += y;}																					// add
	//void matrix::sub(const matrix& y){*this -= y;}																					// sub


	matrix adjoint()const;														//adjoint
	matrix transpose()const;													//transpose

	matrix factor(const int,const int)const;									//produces n-1 order matric
	double det()const;															//determinate
	matrix inverse()const;														//inverse
private:
	short n,m;																	//dimension of a matrix
	double **p;																	//matrix to store double
	struct error;
};//end matrix

matrix operator*(const double&, const matrix&);
matrix operator/(const double&, const matrix&);


///////////////////////////////////////////////////////////////////////////////////struct error
struct matrix::error{
	std::string s;
	error(std::string ss):s(ss){}
//	error(const char ss[]):s(ss){}
	~error(){}																	//destructor
};//end error

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
std::ostream& operator<<(std::ostream& os,const matrix& x){
	for(int i=0;i<x.n;i++){
		for(int j=0;j<x.m;j++)
			os<<x.p[i][j]<<"\t";
		os<<std::endl;
	}
	return os;
}

std::istream& operator>>(std::istream& is,matrix& x){
	for(int i=0;i<x.n;i++)for(int j=0;j<x.m;j++)is>>x.p[i][j];
	return is;
}

matrix::matrix(int nn,int mm):n(nn),m(mm){										//normal constructor
		p=new double*[n];
		for(int i=0;i<n;i++)p[i] = new double[m];
		for(int i=0;i<n;i++)for(int j=0;j<m;j++)p[i][j]=0;
		//std::cout <<"Constructor\n";
}

matrix::matrix(const matrix & t):n(t.n),m(t.m){												//copy constructor
	p=new double*[n];
	for(int i=0;i<n;i++)p[i] = new double[m];
	for(int i=0;i<n;i++)for(int j=0;j<m;j++)p[i][j]=t.p[i][j];
	//std::cout <<"copy constructor\n";
}//end of copy constructor

matrix& matrix:: operator = (const matrix&t){												//copy assignment operator
	if(this!=&t){
		for(int i=0;i<n;i++)delete[] p[i];
		delete[] p;
		n=t.n;
		m=t.m;
		p=new double*[n];
		for(int i=0;i<n;i++)p[i] = new double[m];
		for(int i=0;i<n;i++)for(int j=0;j<m;j++)p[i][j]=t.p[i][j];
	}
	//std::cout <<"copy via assignment\n";
	return *this;
}//end of copy assignment

matrix::~matrix(){																							//destructor
	for(int i=0;i<n;i++)
		delete[] p[i];
	delete[] p;
	/*std::cout <<"Destructor\n";*/
}


inline void matrix::get(){for(int i=0;i<this->n;i++)for(int j=0;j<this->m;j++) p[i][j]=rand()%10;}					//end get
inline void matrix::show()const{for(int i=0;i<this->n;i++){for(int j=0;j<this->m;j++)std::cout << p[i][j] <<"\t";std::cout <<std::endl;}}		//end show


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////arithmetic operatpor
matrix& matrix::operator += (const matrix& y){
	if(n!=y.n||m!=y.m)	throw error("~~~~Can't add as two matrix are not have same dimension~~~~");					//throw error
	for(int i=0;i<this->n;i++)
		for(int j=0;j<this->m;j++)
			p[i][j] += y.p[i][j];
	return *this;
}//end +=

matrix& matrix::operator -= (const matrix& y){
	if(n!=y.n||m!=y.m)	throw error("~~~~Can't subtract as two matrix are not have same dimension~~~~");				//throw error
	for(int i=0;i<this->n;i++)
		for(int j=0;j<this->m;j++)
			p[i][j] -= y.p[i][j];
	return *this;
}//end -=

matrix& matrix::operator*=(const matrix& y){												// *
	if(this->m!=y.n)throw error("~~~~Can't multiply as coloum of first matrix is not equal to row of other~~~~");				//throw
	matrix z(this->n,y.m);
	for(int i=0;i<this->n;i++)
		for(int j=0;j<y.m;j++){
			double sum =0;
			for(int k=0;k<this->m;k++)
				sum+=p[i][k]*y.p[k][j];
			z.p[i][j] = sum;
		}
	(*this) = z;
//	z.~matrix();
	return *this;
}//end *

matrix& matrix::operator/=(const matrix& y){													// /
	matrix z(y);
	z = z.inverse();
	(*this) *= z;
//	z.~matrix();
	return (*this);
}//end /


matrix& matrix::operator *= (const double& num){
	for(int i = 0;i < n;i++)
		for(int j = 0;j < m;j++)
			p[i][j] *= num;
	return *this;
}

matrix& matrix::operator /= (const double& num){
	for(int i = 0;i < n;i++)
		for(int j = 0;j < m;j++)
			p[i][j] /= num;
	return *this;
}

matrix matrix::operator+(const matrix& y)const{
	matrix z(*this);
	z += y;
	return z;
}									//end +
matrix matrix::operator-(const matrix& y)const{
	matrix z = (*this);
	z -= y;
	return z;
}									//end -
matrix matrix::operator*(const matrix& y)const{												// *
	matrix z = (*this);
	z *= y;
	return z;
}//end *
matrix matrix::operator/(const matrix& y)const{													// /
	matrix z(*this);
	z /= y;
	return z;
}//end /

matrix matrix::operator*(const double& num)const{
	matrix z(*this);
	z *= num;
	return z;
}

matrix matrix::operator/(const double& num)const{
	matrix z(*this);
	z /= num;
	return z;
}

matrix operator*(const double& num, const matrix& x){
	return x*num;
}

matrix operator/(const double& num, const matrix& x){
	matrix y(x);
	y = y.inverse();
	return num*y;
}

matrix matrix::transpose()const{														//traspose
	matrix y(m,n);
	for(int i=0;i<n;i++)for(int j=0;j<m;j++) y.p[j][i]=p[i][j];
	return y;
}//end transpose

inline matrix matrix::factor(const int ii,const int jj)const{										// factor to find a matrix of n-1 order
	matrix temp(n-1,m-1);
	for(int i=0,iii=0;i<n;i++){
		if(i==ii)continue;
		for(int j=0,jjj=0;j<m;j++){
			if(j==jj)continue;
			temp.p[iii][jjj++] = p[i][j];
		}
		iii++;
	}
	return temp;
}//end factor

inline double matrix::det()const{														//determinant
	double sum =0;
	if(n==1)return p[0][0];
	double sign =1;
	for(int i=0;i<n;i++){
		sum = sum+sign*p[0][i]*(((*this).factor(0,i)).det());
		sign = -sign;
	}
	return sum;
}//end determinant

matrix matrix::adjoint()const{														//adjoint
	if(n!=m)throw error("~~~~Adjoint exist only for square matrix~~~~");
	int x=1,y;
	double xx=(*this).det();
	if(xx == 0) throw error("~~~~Adjoint of a matrix doesn't exist~~~~");								//throw
	matrix temp(n,m);
	for(int i=0;i<n;i++){
		y=x;
		for(int j=0;j<m;j++){
			temp.p[i][j] = (y*((*this).factor(i,j)).det());
			y=-y;
		}
		x=-x;
	}
	temp=temp.transpose();
	return temp;
}//end adjoint

matrix matrix::inverse()const{														//inverse
	if(n!=m)throw error("~~~~Inverse exist only for square matrix~~~~");									//throw
	if(!(*this).det())throw	error("~~~~Inverse doesn't exist(determinant = 0)~~~~");							//throw
	return (*this).adjoint()/(*this).det();
}//end inverse
