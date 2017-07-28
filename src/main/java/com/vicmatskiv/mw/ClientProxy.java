package com.vicmatskiv.mw;

import com.vicmatskiv.mw.CommonProxy;

public class ClientProxy extends CommonProxy {
   protected boolean isClient() {
      return true;
   }
}
