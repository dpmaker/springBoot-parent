package boot.start.example;

import java.lang.annotation.Annotation;

import org.springframework.stereotype.Component;

import boot.start.Section;


@Component
public class TestScanner extends AbstractBasicScannerRegister{

	@Override
	protected Class<? extends Annotation> returnAnnotationType() {
		return Section.class;
	}

	

}
