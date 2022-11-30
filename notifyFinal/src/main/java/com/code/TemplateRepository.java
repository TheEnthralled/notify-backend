package com.code;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.ResponseBody;

public interface TemplateRepository extends CrudRepository<Template, Integer>
{
	@Query(value = "SELECT * FROM templates WHERE template_id = :reqTemplate", nativeQuery=true)
	public @ResponseBody Template getTemplate(@Param("reqTemplate") int reqTemplate);
}
