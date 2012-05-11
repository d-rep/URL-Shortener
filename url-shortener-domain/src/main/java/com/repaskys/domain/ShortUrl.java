/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.repaskys.domain;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import com.repaskys.url.algorithms.AlphaNumericCodec;
import com.repaskys.url.algorithms.Codec;

/**
 * This class is used to save URL's and their corresponding shortened code.
 *
 * @author Drew Repasky
 */
@Entity
public class ShortUrl implements Serializable {

   private static final long serialVersionUID = 7827630535667254219L;
   
   private static final Codec CODEC = new AlphaNumericCodec();
   private static final int MAX_URL_LENGTH = 500;
   
   /**
    * Helper method.
    * 
    * @see http://stackoverflow.com/questions/1590831/safely-casting-long-to-int-in-java
    */
   private static int safeLongToInt(long l) {
      if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
         throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
      }
      return (int) l;
   }
   
   public static Long shortCodeToId(String shortCode) {
	   int i = CODEC.decode(shortCode);
	   return new Long(i);
   }

   @Id
   @GeneratedValue(strategy = AUTO)
   private Long id;

   @NotBlank(message = "Full URL {javax.validation.constraints.NotBlank.message}")
   @Size(max = MAX_URL_LENGTH, message="Full URL {javax.validation.constraints.Size.message}")
   @URL
   private String fullUrl;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFullUrl() {
      return this.fullUrl;
   }

   /**
    * A URL such as http://www.google.com
    */
   public void setFullUrl(String fullUrl) {
      this.fullUrl = fullUrl;
   }

   @Transient
   public String getShortUrl() {
      // FIXME precision can be lost
      int integerId = safeLongToInt(this.getId());
      return CODEC.encode(integerId);
   }

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
