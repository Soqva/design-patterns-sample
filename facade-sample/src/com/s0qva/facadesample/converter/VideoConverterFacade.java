package com.s0qva.facadesample.converter;

import com.s0qva.facadesample.comlex.system.audio.AudioMixer;
import com.s0qva.facadesample.comlex.system.codec.Codec;
import com.s0qva.facadesample.comlex.system.codec.Mpeg4CompressionCodec;
import com.s0qva.facadesample.comlex.system.codec.OggCompressionCodec;
import com.s0qva.facadesample.comlex.system.codec.factory.CodecFactory;
import com.s0qva.facadesample.comlex.system.domain.VideoFile;
import com.s0qva.facadesample.comlex.system.format.VideoFormat;
import com.s0qva.facadesample.comlex.system.reader.BitrateReader;

/**
 * Facade for the complex classes system
 */
public class VideoConverterFacade {

    public VideoFile convert(String filename, VideoFormat format) {
        VideoFile videoFile = new VideoFile(filename);
        Codec source = CodecFactory.extract(videoFile);
        Codec destination = resolveDestinationCodec(format);
        byte[] destinationVideoBytes = readAndConvertVideoBytes(filename, source, destination);
        byte[] result = fixConvertedVideoBytes(destinationVideoBytes);
        return new VideoFile(destinationVideoBytes);
    }

    private Codec resolveDestinationCodec(VideoFormat format) {
        if (format == VideoFormat.MP4) {
            return new Mpeg4CompressionCodec();
        }
        return new OggCompressionCodec();
    }

    private byte[] readAndConvertVideoBytes(String filename, Codec source, Codec destination) {
        BitrateReader bitrateReader = new BitrateReader();
        byte[] videBytesRead = readVideoBytes(bitrateReader, filename, source);
        return convertTo(bitrateReader, videBytesRead, destination);
    }

    private byte[] fixConvertedVideoBytes(byte[] convertedVideoBytes) {
        AudioMixer audioMixer = new AudioMixer();
        return audioMixer.fix(convertedVideoBytes);
    }

    private byte[] readVideoBytes(BitrateReader bitrateReader, String filename, Codec source) {
        return bitrateReader.read(filename, source);
    }

    private byte[] convertTo(BitrateReader bitrateReader, byte[] VideoBytesRead, Codec destination) {
        return bitrateReader.convert(VideoBytesRead, destination);
    }
}
