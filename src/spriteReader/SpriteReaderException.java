package spriteReader;
@SuppressWarnings("serial")
public class SpriteReaderException extends Exception{
	protected SpriteReaderException(){
		super("Raster is out of bounds");
	}
}
