PImage pic;

void setup(){
    size(1000, 1000);
    pic = loadImage("C:\\Users\\ily\\Desktop\\gal.jpg");
    // pic = loadImage("gal.jpg");
}

void draw() {
    background(0);

    int mountX = 300;
    int mountY = 300;

    pic.resize(mountX, mountY);

    float w = (float) width / mountX;
    float h = (float) width / mountY;

    for(int i = 0; i < mountX; i++) {
        for(int j = 0; j < mountY; j++) {
            color c = pic.get(i, j);
            float bri = brightness(c);

            push();
            translate(i * w, j * h);
            if (bri > 80) {
                float conf = map(bri,
                0, 255,
                0, 1);
                fill(255);
                ellipseMode(CORNER);
                ellipse(0, 0, w * conf, h);
            }
            pop();
        }
    }
}
