package com.s0qva.facadesample;

import com.s0qva.facadesample.comlex.system.domain.VideoFile;
import com.s0qva.facadesample.comlex.system.format.VideoFormat;
import com.s0qva.facadesample.converter.VideoConverterFacade;

public class FacadeSampleRunner {
    private static final String FILENAME = "input.ogg";
    private static final VideoFormat VIDEO_FORMAT = VideoFormat.MP4;

    public static void main(String[] args) {
        VideoConverterFacade videoConverter = new VideoConverterFacade();
        VideoFile video = videoConverter.convert(FILENAME, VIDEO_FORMAT);
    }
}
