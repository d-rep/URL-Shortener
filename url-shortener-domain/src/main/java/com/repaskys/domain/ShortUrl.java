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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import static javax.persistence.GenerationType.AUTO;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * This class is used to save URL's and their corresponding shortened code.
 *
 * @author Drew Repasky
 */
@Entity
// When defining the database, this makes sure that the shortUrl field is unique.
// but we have to do this with the table schema and NOT as part of validation:
// ref: http://stackoverflow.com/questions/3495368/unique-constraint-with-jpa-and-bean-validation#3499111
@Table(uniqueConstraints=@UniqueConstraint(columnNames="shortUrl"))
public class ShortUrl implements Serializable {

	private static final long serialVersionUID = 7827630535667254219L;

@Id
   @GeneratedValue(strategy = AUTO)
   private Long id;

   @NotBlank(message = "Full URL {javax.validation.constraints.NotBlank.message}")
   @Size(max = 500, message="Full URL {javax.validation.constraints.Size.message}")
   @URL
   private String fullUrl;

   @NotBlank(message = "Short URL {javax.validation.constraints.NotBlank.message}")
   @Size(max = 20, message="Short URL {javax.validation.constraints.Size.message}")
   @Pattern(regexp = "[a-zA-Z]*", message="Short URL must be all letters")
   private String shortUrl;

   public Long getId() {
      return this.id;
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

   public String getShortUrl() {
      return this.shortUrl;
   }

   /**
    * Short URL must be a string of all English characters.
    */
   public void setShortUrl(String shortUrl) {
      this.shortUrl = shortUrl;
   }
}
