package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.CustomRenderer;
import java.util.List;

public interface ModelSource {
   List getTexturedModels();

   CustomRenderer getPostRenderer();
}
