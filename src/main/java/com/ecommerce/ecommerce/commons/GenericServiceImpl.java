package com.ecommerce.ecommerce.commons;

import org.springframework.stereotype.Service;

import java.io.Serializable;
@Service
public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericServiceApi<T, ID>{
    

}
