/*
 * Copyright 2016 Google Inc. All Rights Reserved.
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

package mus.HendlStallChecker.test;

import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.common.collect.ImmutableSet;

import mus.periphery.ImageTagger;

/**
 * Integration (system) tests for {@link LabelApp}.
 */
@RunWith(JUnit4.class)
public class ImageTaggerTest {
	private static final int MAX_LABELS = 10;

	private ImageTagger appUnderTest;

	@Before
	public void setUp() throws Exception {
		appUnderTest = new ImageTagger(ImageTagger.getVisionService());
	}

	@Test
	public void testExtractedImage() throws Exception {
		String tmpDir = System.getProperty("java.io.tmpdir");
		String file = Paths.get(tmpDir, "onvif.jpg").toAbsolutePath().toString();

		List<EntityAnnotation> labels = appUnderTest.labelImage(Paths.get(file), MAX_LABELS);

		ImmutableSet.Builder<String> builder = ImmutableSet.builder();
		for (EntityAnnotation label : labels) {
			builder.add(label.getDescription());
		}
		ImmutableSet<String> descriptions = builder.build();

		System.out.print(descriptions);
	}
}
