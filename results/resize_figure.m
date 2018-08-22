function resize_figure(fig, width, height)
    pos = get(fig, "position");
    pos(3) = width;
    pos(4) = height;
    set(fig, "position", pos)
    set(fig, "paperunits", "points");
    set(fig, "paperposition", [0 0 pos(3) pos(4)]);
end
