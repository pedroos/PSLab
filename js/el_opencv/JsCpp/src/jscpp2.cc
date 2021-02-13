#include <napi.h>
#include "MyCode.h"

using namespace Napi;

//extern "C" {
//    __declspec(dllimport) std::string MyCode::MyStr();
//}

Napi::String Method(const Napi::CallbackInfo& info) {
  Napi::Env env = info.Env();
  MyCode mc;
  return Napi::String::New(env, mc.MyStr());
}

Napi::Object Init(Napi::Env env, Napi::Object exports) {
  exports.Set(Napi::String::New(env, "Jscpp2"),
              Napi::Function::New(env, Method));
  return exports;
}

NODE_API_MODULE(addon, Init)
