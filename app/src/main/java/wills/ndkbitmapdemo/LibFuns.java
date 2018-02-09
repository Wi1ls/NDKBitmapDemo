package wills.ndkbitmapdemo;

/**
 * Copyright (c) 2017, Bongmi
 * All rights reserved
 * Author: shenwei@bongmi.com
 */

public class LibFuns {
  static {
    System.loadLibrary("native-lib");
  }

  public static native int[] ImgToGray(int[] buf, int w, int h);
}
