import java.util.List;
import java.util.Arrays;

public class ShortenerCodecImpl implements ShortenerCodec {
   private static final char[] MAPPING = 
      {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

   private static char getMapping(int index) {
      return MAPPING[index];
   }

   @Override
   public String encode(int id) {
      char letter = getMapping(id-1);
      String letters = Character.toString(letter);
      return letters;
   }

   @Override
   public long decode(String code) {
      return 0;
   }
}
