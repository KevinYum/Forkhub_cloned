package com.github.mobile.aspectJ;

import android.support.v7.app.ActionBar;

import com.github.mobile.ui.roboactivities.RoboActionBarActivity;
import com.github.mobile.util.AvatarLoader;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.eclipse.egit.github.core.User;

/**
 * Aspect definition
 */

@Aspect
public class AvatarBindAspect {

    @After("execution(@com.github.mobile.aspectJ.AvatarBind * *(..))")
    public void bind(JoinPoint joinPoint){
        RoboActionBarActivity activity = (RoboActionBarActivity)joinPoint.getThis();
        AvatarLoader avatar = activity.avatar;
        ActionBar actionBar = activity.actionBar;
        User user = activity.user;
        avatar.bind(actionBar, user);
    }


}
