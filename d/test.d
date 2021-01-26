import std;

class S1 {
}

class S2 : S1 {
}

template TS1(T) {
    class S3 {
        public:
        	T x = 2;
    }
}

void main()
{
    writeln("Hello D");
    auto ts = new TS1!(int).S3;
    writeln(ts.x);
}