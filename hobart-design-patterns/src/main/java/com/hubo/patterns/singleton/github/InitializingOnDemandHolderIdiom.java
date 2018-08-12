package com.hubo.patterns.singleton.github;

/**
 * 内部类
 */
public final class InitializingOnDemandHolderIdiom {
    
    private InitializingOnDemandHolderIdiom(){}
    
    private static class HelperHolder{
        private static final InitializingOnDemandHolderIdiom INSTANCE=
                new InitializingOnDemandHolderIdiom();
    }
    
    public static InitializingOnDemandHolderIdiom getInstance(){
        return HelperHolder.INSTANCE;
    }
}
