import java.util.*;

class LZ77 {
    
    //tags
    static class tags {
        
        int position;
        int length;
        Character nextChar;
        
        tags(int position, int length, Character nextChar) {
            this.position = position;
            this.length = length;
            this.nextChar = nextChar;
        }
        
        @Override
        public String toString() {
            String s ="null";
            if(nextChar!=null) s = nextChar.toString();
            return "<" + position + ", " + length + ", " + s + " >";
        }
    }
    
    // Compression 
    public List<tags> compress(String input, int windowSize, int lookAheadBuffer) {
        
        int searchW = windowSize - lookAheadBuffer;
        List<tags> tagList = new ArrayList<>();
        int index = 0;
        
        while (index < input.length()) {
            
            int Start = Math.max(0, index - searchW);
            int maxMatchLength = 0;
            int matchPosition = 0;
            
            for (int i = Start; i < index; i++) {
                
                int length = 0;
                while (length < lookAheadBuffer-1 && 
                       index + length < input.length() && 
                       input.charAt(i + length) == input.charAt(index + length)) {
                    length++;
                }
                
                if (length > maxMatchLength || 
                    (length == maxMatchLength && (index - i) < matchPosition)) {
                    maxMatchLength = length;
                    matchPosition = index - i;
                }
            }
            
            Character nextChar = (index + maxMatchLength < input.length()) ? input.charAt(index + maxMatchLength) : null;
            tagList.add(new tags(matchPosition, maxMatchLength, nextChar));
            index += maxMatchLength + 1;
        }
        
        return tagList;
    }
    
    //Decompression 
    public String decompress(List<tags> compressedData) {
        String decompressed = "";

        for (tags tag : compressedData) {
            int start = decompressed.length() - tag.position;

            for (int i = 0; i < tag.length; i++) {
                decompressed += decompressed.substring(start +i, start +i+1 );
            }

            if (tag.nextChar != null) {
                decompressed += tag.nextChar;
            }
        }

        return decompressed;
    }
    
    //main
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        LZ77 lz77 = new LZ77();
        System.out.print("Enter '1' to Compress or '2' to Decompress: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        
        if (choice == 1) {
            
            System.out.print("Enter the string to compress: ");
            
            String input = scanner.nextLine();
            System.out.print("Enter the window size: ");
            
            int windowSize = scanner.nextInt();
            System.out.print("Enter the lookahead buffer size: ");
            
            int lookAheadBuffer = scanner.nextInt();
            List<tags> compressedData = lz77.compress(input, windowSize, lookAheadBuffer);
            System.out.println("Compressed Data:");
            
            for (tags tag : compressedData) {
                System.out.println(tag);
            }
            
        } else if (choice == 2) {
            
            List<tags> compressedData = new ArrayList<>();
            System.out.print("Enter the number of tags: ");
            
            int tagsCount = scanner.nextInt();
            for (int i = 0; i < tagsCount; i++) {
                
                System.out.print("Enter position, length, and nextChar for tag " + (i + 1) + ": ");
                
                int position = scanner.nextInt();
                int length = scanner.nextInt();
                String nextCharInput = scanner.next();
                Character nextChar = nextCharInput.equals("null") ? null : nextCharInput.charAt(0);
                compressedData.add(new tags(position, length, nextChar));
            }
            
            String decompressedData = lz77.decompress(compressedData);
            System.out.println("Decompressed Data: " + decompressedData);
            
        }
        scanner.close();
    }
}