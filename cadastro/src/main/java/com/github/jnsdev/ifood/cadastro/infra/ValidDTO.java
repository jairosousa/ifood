package com.github.jnsdev.ifood.cadastro.infra;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Autor Jairo Nascimento
 * @Created 01/01/2022 - 18:46
 */
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValidDTOValidator.class })
@Documented
public @interface ValidDTO {

    String message() default "{com.github.jnsdev.ifood.cadastro.infra.ValidDTO.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
